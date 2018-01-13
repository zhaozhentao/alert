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
 * Created by zhaotao on 2017/12/31.
 */

public class ResetPasswordActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.reset_password_activity;
    }

    @Override
    protected void onActivityCreate() {
        setOnClickListeners(new int[]{R.id.reset}, this);
    }

    private void 修改密码() {
        String oldPassword = ((EditText) findViewById(R.id.old_password)).getText().toString();
        String newPassword = ((EditText) findViewById(R.id.new_password)).getText().toString();
        String confirm = ((EditText) findViewById(R.id.new_password_confirm)).getText().toString();

        if (!StringUtils.equals(newPassword, confirm)) {
            Toast.makeText(this, "请确认新密码", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiModule.修改管理员密码(App().getUser().getMobilePhone(), oldPassword, newPassword, new 修改密码回调(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reset:
                修改密码();
                break;
        }
    }

    private static class 修改密码回调 extends BaseRequestListener<ResetPasswordActivity> {

        private 修改密码回调(ResetPasswordActivity activity) {
            super(activity);
        }

        @Override
        protected void onSuccess(ResetPasswordActivity parent, String data) {
            Toast.makeText(parent, "修改密码成功", Toast.LENGTH_SHORT).show();
            parent.finish();
        }

        @Override
        protected void onFailed(ResetPasswordActivity parent, HttpError error) {
            Toast.makeText(parent, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
