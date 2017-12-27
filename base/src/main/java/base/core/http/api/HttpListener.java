package base.core.http.api;


import base.core.http.response.ApiResponse;
import base.core.http.response.HttpError;

/**
 * Created by zhihui_chen on 14-8-13.
 */
public interface HttpListener<T> {
    public void handleResponse(ApiResponse response);

    public void handleError(HttpError httpError);

    public void onStart();

    public void onSuccess(T response);

    public void onFailure(HttpError error);

    public void onFinish();
}
