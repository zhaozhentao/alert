package com.alert.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alert.base.BaseRequestListener;
import com.alert.module.ApiModule;
import com.rctd.platfrom.rcpingan.R;

import base.core.http.response.HttpError;
import base.utils.StringUtils;

/**
 * Created by zhaotao on 2018/1/14.
 */

public class ResetPasswordFromVCodeActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_reset_password;
    }

    @Override
    protected void onActivityCreate() {
        setOnClickListeners(new int[]{R.id.confirm}, this);
    }

    private void 修改密码() {
        String password = ((EditText) findViewById(R.id.password)).getText().toString();
        String passwordConfirm = ((EditText) findViewById(R.id.password_confirm)).getText().toString();

        if (StringUtils.equals(password, passwordConfirm)) {
            Toast.makeText(this, "请确认两次输入的密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String tempKey = getIntent().getStringExtra("tempKey");
        ApiModule.忘记密码更新密码(password, tempKey, new 修改密码回调(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm:
                修改密码();
                break;
        }
    }

    private static class 修改密码回调 extends BaseRequestListener<ResetPasswordFromVCodeActivity> {

        private 修改密码回调(ResetPasswordFromVCodeActivity activity) {
            super(activity);
        }

        @Override
        protected void onSuccess(ResetPasswordFromVCodeActivity parent, String data) {
            Toast.makeText(parent, "修改成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onFailed(ResetPasswordFromVCodeActivity parent, HttpError error) {
            Toast.makeText(parent, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
