package base.core.http.api;

import android.util.Log;

import base.core.http.response.ApiResponse;
import base.core.http.response.HttpError;
import base.utils.StringUtils;
import base.core.http.HttpConstants.Error;

import org.json.JSONObject;

/**
 * 请求返回监听器
 * <p/>
 * Created by zhihui_chen on 14-8-14.
 */
public abstract class ApiHttpListener implements HttpListener<ApiResponse> {
    private static final String TAG = ApiHttpListener.class.getName();

    @Override
    public void handleResponse(ApiResponse response) {
        if (StringUtils.isEmpty(response.getResponse())) {
            HttpError httpError = new HttpError();
            httpError.setCode(Error.RESPONSE_NULL.getCode());
            httpError.setMessage(Error.RESPONSE_NULL.getMessage());
            onFailure(httpError);
            return;
        }
        try {
            onSuccess(response);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "转换接口数据错误 " + response, e);
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
                httpError.setMessage(httpError.getResponse());
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
