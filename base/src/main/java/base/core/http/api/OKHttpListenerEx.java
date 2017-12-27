package base.core.http.api;


import base.core.http.response.HttpError;

import java.lang.ref.WeakReference;

/**
 * Created by zzt on 2015/6/23.
 */
public abstract class OKHttpListenerEx<T> extends OKHttpListener{

    protected WeakReference<T> mActivity;

    public OKHttpListenerEx(T activity){
        mActivity = new WeakReference<T>(activity);
    }

    protected T getActivity(){
        return mActivity.get();
    }

    @Override
    public void onSuccess() {
        T activity = mActivity.get();
        if(activity != null){
            onSuccessCallBack(activity);
        }
    }

    @Override
    public void onFailure(HttpError error) {
        T activity = mActivity.get();
        if(activity != null){
            onFailureCallBack(error, activity);
        }
    }

    public abstract void onSuccessCallBack(T activity);
    public abstract void onFailureCallBack(HttpError error, T activity);
}
