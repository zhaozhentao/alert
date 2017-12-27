package base.core.http.api;


import base.core.http.response.HttpError;

import org.json.JSONObject;

import java.lang.ref.WeakReference;

/**
 * Created by zzt on 2015/6/23.
 */
public abstract class JsonObjectHttpListenerEx<T> extends JsonObjectHttpListener{

    protected WeakReference<T> mActivity;

    public JsonObjectHttpListenerEx(T activity){
        mActivity = new WeakReference<T>(activity);
    }

    protected T getActivity(){
        return mActivity.get();
    }

    @Override
    public void onSuccess(JSONObject response) {
        T activity = mActivity.get();
        if(activity != null){
            onSuccessCallBack(response, activity);
        }
    }

    @Override
    public void onFailure(HttpError error) {
        T activity = mActivity.get();
        if(activity != null){
            onFailureCallBack(error, activity);
        }
    }

    public abstract void onSuccessCallBack(JSONObject response, T activity);
    public abstract void onFailureCallBack(HttpError error, T activity);
}
