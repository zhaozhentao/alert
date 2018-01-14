package com.alert.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alert.base.BaseRequestListener;
import com.alert.module.ApiModule;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rctd.platfrom.rcpingan.R;

import java.util.HashMap;

import base.core.http.response.HttpError;

/**
 * Created by zhaotao on 2018/1/14.
 */

public class ForgotPasswordActivity extends BaseActivity implements View.OnClickListener {

    private String tempKey;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_forgot_password;
    }

    @Override
    protected void onActivityCreate() {
        setOnClickListeners(new int[]{R.id.reset, R.id.getMsgCode}, this);
    }

    private void 重置密码() {
        String vCode = ((EditText) findViewById(R.id.vCode)).getText().toString();
        ApiModule.忘记密码验证用户信息(vCode, tempKey, new 忘记密码验证用户信息回调(this));
    }

    private void 获取短信验证码() {
        String phone = ((EditText) findViewById(R.id.phone)).getText().toString();
        ApiModule.获取短信验证码(phone, new 获取短信码回调(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reset:
                重置密码();
                break;
            case R.id.getMsgCode:
                获取短信验证码();
                break;
        }
    }

    private static class 获取短信码回调 extends BaseRequestListener<ForgotPasswordActivity> {

        private 获取短信码回调(ForgotPasswordActivity activity) {
            super(activity);
        }

        @Override
        protected void onSuccess(ForgotPasswordActivity parent, String data) {
            HashMap<String, String> map = new Gson().fromJson(data, new TypeToken<HashMap<String, String>>() {
            }.getType());
            parent.tempKey = map.get("tempKey");
        }

        @Override
        protected void onFailed(ForgotPasswordActivity parent, HttpError error) {
            Toast.makeText(parent, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private static class 忘记密码验证用户信息回调 extends BaseRequestListener<ForgotPasswordActivity> {

        private 忘记密码验证用户信息回调(ForgotPasswordActivity activity) {
            super(activity);
        }

        @Override
        protected void onSuccess(ForgotPasswordActivity parent, String data) {
            HashMap<String, String> map = new Gson().fromJson(data, new TypeToken<HashMap<String, String>>() {
            }.getType());
            Intent intent = new Intent(parent, ResetPasswordFromVCodeActivity.class);
            intent.putExtra("tempKey", map.get("tempKey"));
            parent.startActivity(intent);
        }

        @Override
        protected void onFailed(ForgotPasswordActivity parent, HttpError error) {
            Toast.makeText(parent, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
