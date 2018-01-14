package com.alert.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alert.base.BaseRequestListener;
import com.alert.base.Counter;
import com.alert.module.ApiModule;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rctd.platfrom.rcpingan.R;

import java.util.HashMap;

import base.core.http.response.HttpError;

import static com.alert.base.Counter.Minute;
import static com.alert.base.Counter.Second;

/**
 * Created by zhaotao on 2018/1/13.
 */

public class BindPhoneActivity extends BaseActivity implements View.OnClickListener {

    private Counter counter;
    private String tempKey;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_bind_phone;
    }

    @Override
    protected void onActivityCreate() {
        setOnClickListeners(new int[]{R.id.bind, R.id.getMsgCode}, this);
    }

    private void 绑定手机() {
        String phone = ((EditText) findViewById(R.id.phone)).getText().toString();
        String vCode = ((EditText) findViewById(R.id.vCode)).getText().toString();
        ApiModule.绑定用户手机号(phone, tempKey, vCode, new 绑定手机回调(this));
    }

    private void 获取短信码() {
        String phone = ((EditText) findViewById(R.id.phone)).getText().toString();
        ApiModule.获取短信验证码(phone, new 获取短信码回调(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (counter != null) {
            counter.cancel();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getMsgCode:
                获取短信码();
                break;
            case R.id.bind:
                绑定手机();
                break;
        }
    }

    private static class 绑定手机回调 extends BaseRequestListener<BindPhoneActivity> {

        private 绑定手机回调(BindPhoneActivity activity) {
            super(activity);
        }

        @Override
        protected void onSuccess(BindPhoneActivity parent, String data) {
            Toast.makeText(parent, "绑定成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onFailed(BindPhoneActivity parent, HttpError error) {
            Toast.makeText(parent, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private static class 获取短信码回调 extends BaseRequestListener<BindPhoneActivity> {

        private 获取短信码回调(BindPhoneActivity activity) {
            super(activity);
        }

        @Override
        protected void onSuccess(BindPhoneActivity parent, String data) {
            Button button = parent.findViewById(R.id.getMsgCode);
            parent.counter = new Counter(Minute, Second,
                (left) -> button.setText("重新获取(" + left + ")"),
                () -> {
                    button.setEnabled(true);
                    button.setText("获取验证码");
                });
            button.setEnabled(false);
            parent.counter.start();

            HashMap<String, String> map = new Gson().fromJson(data, new TypeToken<HashMap<String, String>>() {
            }.getType());
            parent.tempKey = map.get("tempKey");
        }

        @Override
        protected void onFailed(BindPhoneActivity parent, HttpError error) {
            Toast.makeText(parent, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
