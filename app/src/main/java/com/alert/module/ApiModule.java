package com.alert.module;

import com.alert.base.BaseRequest;
import com.alert.consts.Urls;
import com.alert.entity.CarInfo;
import com.alert.entity.CardReader;

import base.core.http.api.HttpListener;
import base.core.http.request.HttpMethod;

/**
 * Created by zhaotao on 2018/1/10.
 */

public class ApiModule {

    private static final String PAGE_SIZE = "10";

    //1
    public static void 获取短信验证码(String mobilePhone, HttpListener listener) {
        new BaseRequest(Urls.获取短信验证码, HttpMethod.POST)
            .addParam("mobilePhone", mobilePhone)
            .send(listener);
    }

    //2
    public static void 登录(String mobilePhone, String passwd, String pushId, HttpListener listener) {
        new BaseRequest(Urls.登录, HttpMethod.POST)
            .addParam("passwd", passwd)
            .addParam("mobilePhone", mobilePhone)
            .addParam("pushId", pushId)
            .send(listener);
    }

    //3
    public static void 忘记密码验证用户信息(String msgCode, String tempKey, HttpListener listener) {
        new BaseRequest(Urls.忘记密码验证用户信息, HttpMethod.POST)
            .addParam("msgCode", msgCode)
            .addParam("tempKey", tempKey)
            .send(listener);
    }

    //4
    public static void 忘记密码更新密码(String passwd, String tempKey, HttpListener listener) {
        new BaseRequest(Urls.忘记密码更新密码, HttpMethod.POST)
            .addParam("passwd", passwd)
            .addParam("tempKey", tempKey)
            .send(listener);
    }

    //5
    public static void 修改管理员密码(String mobilePhone, String passwdOld, String passwd, HttpListener listener) {
        new BaseRequest(Urls.修改管理员密码, HttpMethod.POST)
            .addParam("mobilePhone", mobilePhone)
            .addParam("passwd", passwd)
            .addParam("passwdOld", passwdOld)
            .send(listener);
    }

    //6
    public static void 检查是否升级(HttpListener listener) {
        new BaseRequest(Urls.检查是否升级, HttpMethod.POST)
            .send(listener);
    }

    //7
    public static void 退出登录(HttpListener listener) {
        new BaseRequest(Urls.退出登录, HttpMethod.POST)
            .send(listener);
    }

    //8
    public static void 上传用户身份证并返回识别结果(String mobilePhone, String vehsId, String userId, HttpListener listener) {
        new BaseRequest(Urls.上传用户身份证并返回识别结果, HttpMethod.POST)
            .addParam("mobilePhone", mobilePhone)
            .addParam("vehsId", vehsId)
            .addParam("userId", userId)
            .send(listener);
    }

    //9
    public static void 通过车牌号或RFID号获取车辆信息(String idValue, HttpListener listener) {
        new BaseRequest(Urls.通过车牌号或RFID号获取车辆信息, HttpMethod.POST)
            .addParam("idValue", idValue)
            .send(listener);
    }

    //10
    public static void 保存车辆信息(CarInfo info, HttpListener listener) {
        new BaseRequest(Urls.保存车辆信息, HttpMethod.POST)
            .setBody(info)
            .send(listener);
    }

    //11
    public static void 上传车辆图片信息(String vehsId, String mobilePhone, String idCardNumber, String userId, HttpListener listener) {
        new BaseRequest(Urls.上传车辆图片信息, HttpMethod.POST)
            .addParam("vehsId", vehsId)
            .addParam("mobilePhone", mobilePhone)
            .addParam("idCardNumber", idCardNumber)
            .addParam("userId", userId)
            .send(listener);
    }

    //12
    public static void 获取广告信息(String areaCode, HttpListener listener) {
        new BaseRequest(Urls.获取广告信息, HttpMethod.POST)
            .addParam("areaCode", areaCode)
            .send(listener);
    }

    //13
    public static void 获取用户区域码(HttpListener listener) {
        new BaseRequest(Urls.获取用户区域码, HttpMethod.POST)
            .send(listener);
    }

    //14
    public static void 用户行车轨迹(String vehsId, String keyword, String beginTime, String endTime, HttpListener listener) {
        new BaseRequest(Urls.用户行车轨迹, HttpMethod.POST)
            .addParam("vehsId", vehsId)
            .addParam("keyword", keyword)
            .addParam("beginTime", beginTime)
            .addParam("endTime", endTime)
            .send(listener);
    }

    //15
    public static void 获取管理人员信息(HttpListener listener) {
        new BaseRequest(Urls.获取管理人员信息, HttpMethod.POST)
            .send(listener);
    }

    //16
    public static void 新增读卡器(CardReader reader, HttpListener listener) {
        new BaseRequest(Urls.新增读卡器, HttpMethod.POST)
            .setBody(reader)
            .send(listener);
    }

    //17
    public static void 修改读卡器(CardReader reader, HttpListener listener) {
        new BaseRequest(Urls.修改读卡器, HttpMethod.POST)
            .setBody(reader)
            .send(listener);
    }

    //18
    public static void 解绑读卡器(String cardId, String managerUserId, HttpListener listener) {
        new BaseRequest(Urls.解绑读卡器, HttpMethod.POST)
            .addParam("cardId", cardId)
            .addParam("managerUserId", managerUserId)
            .send(listener);
    }

    //19
    public static void 删除安装点(String cardId, HttpListener listener) {
        new BaseRequest(Urls.删除安装点, HttpMethod.POST)
            .addParam("cardId", cardId)
            .send(listener);
    }

    //20
    public static void 读卡器信息查询(
        String cardReaderNumber, String cardReaderAreaCode, String cardReaderStatus, String installStatus,
        String beginTime, String endTime, int pageIndex, HttpListener listener
    ) {
        new BaseRequest(Urls.读卡器信息查询, HttpMethod.POST)
            .addParam("cardReaderNumber", cardReaderNumber)
            .addParam("cardReaderAreaCode", cardReaderAreaCode)
            .addParam("cardReaderStatus", cardReaderStatus)
            .addParam("installStatus", installStatus)
            .addParam("beginTime", beginTime)
            .addParam("endTime", endTime)
            .addParam("pageSize", PAGE_SIZE)
            .addParam("pageIndex", pageIndex + "")
            .send(listener);
    }

    //21
    public static void 绑定用户手机号(String userMobile, HttpListener listener) {
        new BaseRequest(Urls.绑定用户手机号, HttpMethod.POST)
            .addParam("userMobile", userMobile)
            .send(listener);
    }

    //22
    public static void 解绑用户手机号(HttpListener listener) {
        new BaseRequest(Urls.解绑用户手机号, HttpMethod.POST)
            .send(listener);
    }
}
