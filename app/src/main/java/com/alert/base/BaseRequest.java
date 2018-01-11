package com.alert.base;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import base.core.http.HttpFactory;
import base.core.http.api.HttpListener;
import base.core.http.request.HttpMethod;
import base.core.http.request.HttpRequest;

/**
 * Created by zhaotao on 2018/1/11.
 */

public class BaseRequest {

    private HashMap<String, String> map = new HashMap<>();

    private HttpRequest request;

    public BaseRequest(String url, HttpMethod method) {
        request = new HttpRequest(url, method);
    }

    public BaseRequest addParam(String key, String value) {
        map.put(key, value);
        return this;
    }

    public void send(HttpListener listener) {
        String body = new Gson().toJson(map);
        try {
            body = URLEncoder.encode(body, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        request.addParam("jsondata", body);
        HttpFactory.getHttpService().sendRequest(request, listener);
    }
}
