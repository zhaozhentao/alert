package base.core.http.request;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * 主体请求类
 * <p/>
 * Created by Tony on 10/18/14.
 */
public class BodyRequest extends Request {

    private byte[] bytes = null;

    public BodyRequest(String url, HttpMethod method) {
        super(url, method);
    }

    public String getBodyContentType() {
        return "application/json; charset=" + getParamsEncoding();
    }

    @Override
    public byte[] getBody() {
        return bytes;
    }

    public void addBody(byte[] bytes) {
        this.bytes = bytes;
    }

    public void addBody(String bodyStr) {
        try {
            this.bytes = bodyStr.getBytes(getParamsEncoding());
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + getParamsEncoding(), uee);
        }
    }

    public void addBody(JSONObject bodyJson) {
        try {
            this.bytes = bodyJson.toString().getBytes(getParamsEncoding());
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + getParamsEncoding(), uee);
        }
    }

    public void addBody(JSONArray bodyJson) {
        try {
            this.bytes = bodyJson.toString().getBytes(getParamsEncoding());
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + getParamsEncoding(), uee);
        }
    }
}
