package base.core.http.api;

import android.util.Log;


import base.core.http.response.ApiResponse;
import base.core.http.response.HttpError;
import base.utils.StringUtils;
import base.core.http.HttpConstants.Error;

import org.json.JSONObject;

/**
 * String请求返回监听器
 * <p/>
 * Created by zhihui_chen on 14-8-14.
 */
public abstract class JsonObjectHttpListener implements HttpListener<JSONObject> {
    private static final String TAG = JsonObjectHttpListener.class.getName();

    @Override
    public void handleResponse(ApiResponse response) {
        if (StringUtils.isEmpty(response.getResponse())) {
            onFailure(new HttpError(Error.RESPONSE_NULL.getCode(), Error.RESPONSE_NULL.getMessage()));
            return;
        }
        try {
            onSuccess(new JSONObject(response.getResponse()));
        } catch (Exception e) {
            Log.e(TAG, "TO JSONObject", e);
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
