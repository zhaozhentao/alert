package com.alert.base;

import android.util.Log;

import org.json.JSONObject;

import java.net.URLDecoder;

import base.core.http.api.ApiHttpListenerEx;
import base.core.http.response.ApiResponse;
import base.core.http.response.HttpError;

/**
 * Created by zhaotao on 2018/1/11.
 */

public abstract class BaseRequestListener<T> extends ApiHttpListenerEx<T> {

    public BaseRequestListener(T activity) {
        super(activity);
    }

    @Override
    public void onSuccess(ApiResponse response, T parent) {
        try {
            String data = URLDecoder.decode(response.getResponse(), "UTF-8");
            JSONObject jsonObject = new JSONObject(data);
            if (jsonObject.getInt("resultCode") == 0) {
                onSuccess(parent, jsonObject.getString("data"));
            } else {
                HttpError error = new HttpError();
                error.setMessage(jsonObject.getString("errorMsg"));
                onFailed(parent, error);
            }
        } catch (Exception e) {
            Log.e("请求出错", e.getMessage() + "");
        }
    }

    @Override
    public void onFailure(HttpError error, T parent) {
        onFailed(parent, error);
    }

    protected abstract void onSuccess(T parent, String data);

    protected abstract void onFailed(T parent, HttpError error);
}
