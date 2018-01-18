package com.alert.base;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import com.alert.App;
import com.google.gson.Gson;
import com.igexin.sdk.PushManager;
import com.rctd.platfrom.rcpingan.BuildConfig;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import base.AppManager;
import base.core.http.HttpFactory;
import base.core.http.api.HttpListener;
import base.core.http.request.HttpMethod;
import base.core.http.request.HttpRequest;
import base.utils.StringUtils;

/**
 * Created by zhaotao on 2018/1/11.
 */

public class BaseRequest {

    public static String token;

    private HashMap<String, String> map = new HashMap<>();

    private HttpRequest request;

    private Object body;

    public BaseRequest(String url, HttpMethod method) {
        request = new HttpRequest(url, method);
    }

    public BaseRequest addParam(String key, String value) {
        map.put(key, value);
        return this;
    }

    public BaseRequest setBody(Object body) {
        this.body = body;
        return this;
    }

    public BaseRequest addHeader(String key, String value) {
        request.addHeader(key, value);
        return this;
    }

    public void send(HttpListener listener) {
        String body;

        if (this.body != null) {
            body = new Gson().toJson(this.body);
        } else {
            body = new Gson().toJson(map);
        }

        try {
            body = URLEncoder.encode(body, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        request.addParam("jsondata", body);

        App app = App.getInstance();
        request.addHeader("clientId", PushManager.getInstance().getClientid(app));
        request.addHeader("appVer", BuildConfig.VERSION_NAME);
        request.addHeader("channelId", "channel");
        if (ActivityCompat.checkSelfPermission(app, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            TelephonyManager telephonyManager = (TelephonyManager) app.getSystemService(app.TELEPHONY_SERVICE);
            request.addHeader("imei", telephonyManager.getDeviceId());
            request.addHeader("imsi", telephonyManager.getSubscriberId());
        }
        request.addHeader("macId", "macId");
        request.addHeader("sysVer", android.os.Build.VERSION.SDK);
        request.addHeader("devType", Build.MODEL);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        AppManager.getInstance().currentActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        request.addHeader("devXy", displayMetrics.widthPixels + ":" + displayMetrics.heightPixels);
        if (StringUtils.isNotEmpty(token)) {
            request.addHeader("appToken", token);
        }

        HttpFactory.getHttpService().sendRequest(request, listener);
    }

    public static void setToken(String token) {
        BaseRequest.token = token;
    }
}
