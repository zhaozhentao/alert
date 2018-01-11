package com.alert;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.alert.model.DaoMaster;
import com.alert.model.DaoSession;
import com.alert.service.PushService;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.igexin.sdk.PushManager;
import com.rctd.platfrom.rcpingan.BuildConfig;

import base.BaseContext;

/**
 * Created by zhaotao on 2018/1/1.
 */

public class App extends Application {

    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        BaseContext.InitLibrary(this);

        initDatabase();
        初始化百度地图();
        初始化推送();
    }

    private void 初始化推送() {
        PushManager.getInstance().initialize(this, PushService.class);
    }

    private void 初始化百度地图() {
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }

    private void initDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, BuildConfig.APPLICATION_ID);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
