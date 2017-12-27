package base.core.http.api;


import base.core.http.response.ApiResponse;
import base.core.http.response.HttpError;

import java.lang.ref.WeakReference;

/**
 * Created by zzt on 2015/6/23.
 */
public abstract class ApiHttpListenerEx<T> extends ApiHttpListener {

    protected WeakReference<T> parent;

    public ApiHttpListenerEx(T activity) {
        parent = new WeakReference<T>(activity);
    }

    protected T getParent() {
        return parent.get();
    }

    @Override
    public void onSuccess(ApiResponse response) {
        T activity = parent.get();
        if (activity != null) {
            onSuccess(response, activity);
        }
    }

    @Override
    public void onFailure(HttpError error) {
        T activity = parent.get();
        if (activity != null) {
            onFailure(error, activity);
        }
    }

    public abstract void onSuccess(ApiResponse response, T parent);

    public abstract void onFailure(HttpError error, T parent);

    public void onFinish(T activity) {

    }

    @Override
    public void onFinish() {
        super.onFinish();

        onFinish(parent.get());
    }
}
