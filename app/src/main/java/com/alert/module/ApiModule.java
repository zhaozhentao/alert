package com.alert.module;

import com.alert.base.BaseRequest;
import com.alert.consts.Urls;

import base.core.http.api.HttpListener;
import base.core.http.request.HttpMethod;

/**
 * Created by zhaotao on 2018/1/10.
 */

public class ApiModule {

    public static void 获取广告信息(String areaCode, HttpListener listener) {
        new BaseRequest(Urls.获取广告信息, HttpMethod.POST)
            .addParam("areaCode", areaCode)
            .send(listener);
    }

    public static void 登录(String mobilePhone, String passwd, HttpListener listener) {
        new BaseRequest(Urls.登录, HttpMethod.POST)
            .addParam("passwd", passwd)
            .addParam("mobilePhone", mobilePhone)
            .send(listener);
    }

    public static void 修改管理员密码(String mobilePhone, String passwdOld, String passwd, HttpListener listener) {
        new BaseRequest(Urls.修改管理员密码, HttpMethod.POST)
            .addParam("mobilePhone", mobilePhone)
            .addParam("passwd", passwd)
            .addParam("passwdOld", passwdOld)
            .send(listener);
    }

    public static void 退出登录(HttpListener listener) {
        new BaseRequest(Urls.退出登录, HttpMethod.POST)
            .send(listener);
    }
}
