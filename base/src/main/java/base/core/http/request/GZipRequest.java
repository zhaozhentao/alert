package base.core.http.request;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import base.core.cache.CacheManager;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;

/**
 * Created by zzt on 2015/4/14.
 */
public class GZipRequest extends StringRequest {
    public GZipRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    public GZipRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    // parse the gzip response using a GZIPInputStream
    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String encoding = response.headers.get("Content-Encoding");

        //saveCookie
        String setCookie = response.headers.get("Set-Cookie");
        if (setCookie != null) {
            HashMap<String, String> map;
            String cacheCookie = CacheManager.getInstance().getString("cache_cookie");
            if (cacheCookie != null) {
                map = new Gson().fromJson(cacheCookie, new TypeToken<HashMap<String, String>>() {
                }.getType());
            } else {
                map = new HashMap<>();
            }

            String cookieContent = setCookie.split(";")[0];
            String cookieName = cookieContent.split("=")[0];
            String cookieValue = cookieContent.split("=")[1];
            map.put(cookieName, cookieValue);
            CacheManager.getInstance().put("cache_cookie", new Gson().toJson(map));
        }

        boolean isSupportGzip = false;
        if (encoding != null && encoding.equals("gzip")) {
            isSupportGzip = true;
        }
        if (isSupportGzip) {
            return getGzipResponse(response);
        } else {
            return getNormalResponse(response);
        }
    }

    private int getShort(byte[] data) {
        return (int) ((data[0] << 8) | data[1] & 0xFF);
    }

    private Response<String> getGzipResponse(NetworkResponse response) {

        InputStream in;
        StringBuilder sb = new StringBuilder();
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(response.data);
            in = new GZIPInputStream(bis);
            BufferedReader r = new BufferedReader(new InputStreamReader(in), 16384);
            for (String line = r.readLine(); line != null; line = r.readLine()) {
                sb.append(line);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return getNormalResponse(response);
        }
        return Response.success(sb.toString(), HttpHeaderParser.parseCacheHeaders(response));
    }

    private Response<String> getNormalResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }
}