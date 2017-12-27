package base.core.http.api;

import android.util.Log;


import base.core.http.response.ApiResponse;
import base.core.http.response.HttpError;
import base.utils.StringUtils;

import org.json.JSONObject;

/**
 * OK，没有请求内容返回
 * <p/>
 * Created by Tony on 10/17/14.
 */
public abstract class OKHttpListener implements HttpListener<String> {
    private static final String TAG = OKHttpListener.class.getName();

    @Override
    public void handleResponse(ApiResponse response) {
        try {
            onSuccess(null);
        } catch (Exception e) {
            Log.e(TAG, "handleResponse", e);
        }
    }

    @Override
    public void onStart() {

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
    public void onSuccess(String response) {
        onSuccess();
    }

    @Override
    public void onFinish() {

    }

    public abstract void onSuccess();
}
