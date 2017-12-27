package base.core.http.api;

import android.util.Log;


import base.core.http.response.ApiResponse;
import base.core.http.response.HttpError;
import base.utils.StringUtils;

import org.json.JSONObject;

/**
 * String请求返回监听器
 * <p/>
 * Created by zhihui_chen on 14-8-14.
 */
public abstract class StringHttpListener implements HttpListener<String> {
    private static final String TAG = StringHttpListener.class.getName();

    @Override
    public void handleResponse(ApiResponse response) {
        try {
            onSuccess(response.getResponse());
        } catch (Exception e) {
            Log.e(TAG, "handleResponse", e);
        }
    }

    @Override
    public void handleError(HttpError httpError) {
        if (StringUtils.isNotEmpty(httpError.getResponse())) {
            try {
                JSONObject jsonObject = new JSONObject(httpError.getResponse());
                if (jsonObject.has("errors")) {
                    httpError.setErrors(jsonObject.getJSONObject("errors"));
                }
                httpError.setMessage(jsonObject.getString("message"));
            } catch (Exception e) {
                Log.e(TAG, "handleError", e);
            }
        }
        onFailure(httpError);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onFinish() {

    }

}
