package com.alert.ui.fragment;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alert.base.BaseRequestListener;
import com.alert.module.ApiModule;
import com.alert.ui.activity.BaseActivity;
import com.rctd.platfrom.rcpingan.R;

import base.core.http.response.HttpError;

/**
 * Created by zhaotao on 2018/1/13.
 */

public class UnBindPhoneActivity extends BaseActivity implements View.OnClickListener {

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

    }

    private void 解绑手机() {
        ApiModule.解绑用户手机号(new 解绑手机回调(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.unBind:
                解绑手机();
                break;
            case R.id.getMsgCode:
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
}
