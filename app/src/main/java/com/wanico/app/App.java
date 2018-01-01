package com.wanico.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.wanico.app.model.DaoMaster;
import com.wanico.app.model.DaoSession;

/**
 * Created by zhaotao on 2018/1/1.
 */

public class App extends Application {

    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        initDatabase();
    }

    private void initDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "brain_wave");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
