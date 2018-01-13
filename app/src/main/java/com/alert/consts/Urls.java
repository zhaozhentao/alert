package com.alert.consts;

import com.rctd.platfrom.rcpingan.BuildConfig;

/**
 * Created by zhaotao on 2017/11/26.
 */

public class Urls {

    public static final String 获取广告信息 = BuildConfig.BASE_URL + "/advs";

    public static final String 登录 = BuildConfig.BASE_URL + "/mg-login";

    public static final String 修改管理员密码 = BuildConfig.BASE_URL + "/mg-modify-pwd";

    public static final String 退出登录 = BuildConfig.BASE_URL + "/logout";

    public static final String 获取短信验证码 = BuildConfig.BASE_URL + "/sms-code";

    public static final String 忘记密码验证用户信息 = BuildConfig.BASE_URL + "/mg-resetpwdvalidate";

    public static final String 忘记密码更新密码 = BuildConfig.BASE_URL + "/mg-resetpwd";

    public static final String 检查是否升级 = BuildConfig.BASE_URL + "/upgrade";

    public static final String 上传用户身份证并返回识别结果 = BuildConfig.BASE_URL + "/mg-idcard-upload";

    public static final String 通过车牌号或RFID号获取车辆信息 = BuildConfig.BASE_URL + "/mg-vehsinfo";

    public static final String 保存车辆信息 = BuildConfig.BASE_URL + "/mg-save-info";

    public static final String 上传车辆图片信息 = BuildConfig.BASE_URL + "/mg-carimg-upload";

    public static final String 获取用户区域码 = BuildConfig.BASE_URL + "/user_area_code";

    public static final String 用户行车轨迹 = BuildConfig.BASE_URL + "/vehs-tracks";

    public static final String 获取管理人员信息 = BuildConfig.BASE_URL + "/manager";

    public static final String 新增读卡器 = BuildConfig.BASE_URL + "/new_card_reader";

    public static final String 修改读卡器 = BuildConfig.BASE_URL + "/modify_card_reader";

    public static final String 解绑读卡器 = BuildConfig.BASE_URL + "/relieve_card_reader";

    public static final String 删除安装点 = BuildConfig.BASE_URL + "/remove_card_reader";

    public static final String 读卡器信息查询 = BuildConfig.BASE_URL + "/list_card_reader";

    public static final String 绑定用户手机号 = BuildConfig.BASE_URL + "/user_bind_mobile";

    public static final String 解绑用户手机号 = BuildConfig.BASE_URL + "/cancel_bind_mobile";
}
