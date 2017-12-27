package base.core.http.response;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;


/**
 * 请求返回的错误
 * 分为http错误 和 服务器内部定制错误
 * <p/>
 * {"message":"验证不通过","errors":{"quantity":{"message":"每位用户最多能抢 1 张免费票"}}}
 * <p/>
 * Created by tony on 9/28/14.
 */
public class HttpError extends ApiResponse {
    private String message = "",data;
    private int number = 0;
    private JSONObject errors;

    public HttpError() {
    }

    public HttpError(int code, String response) {
        super(code, response);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNumber() {
        return number;
    }
    public String getdata() {
        return data;
    }public void setdata(String data) {
        this.data = data;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setErrors(JSONObject errors) {
        this.errors = errors;
    }

    /**
     * 是否包含了该错误
     *
     * @param key
     * @return
     */
    public boolean containsKey(String key) {
        return errors.has(key);
    }

    /**
     * 获取错误
     *
     * @param key
     * @return
     */
    public String getError(String key) {
        if (errors != null && containsKey(key)) {
            try {
                return errors.getJSONObject(key).getString("message");
            } catch (JSONException e) {
                Log.e("HttpError", key, e);
            }
        }
        return "";
    }

    /**
     * 产生一个toast
     *
     * @param context
     */
    public void makeToast(Context context) {
        // 遍历所有错误信息
        if (errors != null && errors.length() > 0) {
            String errmsg = "";
            Iterator<String> it = errors.keys();
            while (it.hasNext()) {
                String key = it.next();
                try {
                    errmsg += errors.getJSONObject(key).getString("message");
                    if (it.hasNext()) {
                        errmsg += "，";
                    }

                } catch (JSONException e) {
                    Log.e("HttpError", key, e);
                }
            }
            return;
        }
    }


}
