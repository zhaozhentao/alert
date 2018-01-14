package com.alert.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class UnBindPhoneActivity extends BaseActivity implements View.OnClickListener {

    private Counter counter;

    private String tempKey;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_un_bind;
    }

    @Override
    protected void onActivityCreate() {
        setOnClickListeners(new int[]{R.id.unBind, R.id.getMsgCode}, this);

        ((TextView) findViewById(R.id.phone)).setText("已绑定手机:" + App().getUser().getLogin().userMobile);
    }

    private void 获取短信码() {
        String phone = App().getUser().getLogin().userMobile;
        ApiModule.获取短信验证码(phone, new 获取验证码回调(this));
    }

    private void 解绑手机() {
        String vCode = ((EditText) findViewById(R.id.vCode)).getText().toString();
        ApiModule.解绑用户手机号(tempKey, vCode, new 解绑手机回调(this));
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
            case R.id.unBind:
                解绑手机();
                break;
            case R.id.getMsgCode:
                获取短信码();
                break;
        }
    }

    private static class 解绑手机回调 extends BaseRequestListener<UnBindPhoneActivity> {

        private 解绑手机回调(UnBindPhoneActivity activity) {
            super(activity);
        }

        @Override
        protected void onSuccess(UnBindPhoneActivity parent, String data) {
            Toast.makeText(parent, "解绑成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onFailed(UnBindPhoneActivity parent, HttpError error) {
            Toast.makeText(parent, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private static class 获取验证码回调 extends BaseRequestListener<UnBindPhoneActivity> {

        private 获取验证码回调(UnBindPhoneActivity activity) {
            super(activity);
        }

        @Override
        protected void onSuccess(UnBindPhoneActivity parent, String data) {
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
        protected void onFailed(UnBindPhoneActivity parent, HttpError error) {
            Toast.makeText(parent, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
