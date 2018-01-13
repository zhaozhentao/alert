package com.alert.ui.fragment;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alert.App;
import com.alert.base.BaseRequestListener;
import com.alert.consts.Consts;
import com.alert.entity.Login;
import com.alert.module.ApiModule;
import com.alert.ui.activity.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rctd.platfrom.rcpingan.R;

import base.core.cache.Cache;
import base.core.cache.CacheManager;
import base.core.http.response.HttpError;

/**
 * Created by zhaotao on 2018/1/13.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onActivityCreate() {
        setOnClickListeners(new int[]{R.id.login}, this);
    }

    private void 登录() {
        String mobilePhone = ((EditText) findViewById(R.id.mobilePhone)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();
        ApiModule.登录(mobilePhone, password, new 登录回调(mobilePhone, this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                登录();
                break;
        }
    }

    //15815628218 账号  密码是123456
    private static class 登录回调 extends BaseRequestListener<LoginActivity> {

        private String mobilePhone;

        private 登录回调(String mobilePhone, LoginActivity activity) {
            super(activity);
            this.mobilePhone = mobilePhone;
        }

        @Override
        protected void onSuccess(LoginActivity parent, String data) {
            Login login = new Gson().fromJson(data, new TypeToken<Login>() {
            }.getType());
            parent.App().setUser(new App.User(login, mobilePhone));

            //cache
            Cache cache = CacheManager.getInstance();
            cache.put(Consts.MobilePhone, mobilePhone);
            cache.put(Consts.Login, data);

            parent.finish();
        }

        @Override
        protected void onFailed(LoginActivity parent, HttpError error) {
            Toast.makeText(parent, "登录失败", Toast.LENGTH_SHORT).show();
        }
    }
}
