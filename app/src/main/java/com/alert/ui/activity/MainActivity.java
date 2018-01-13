package com.alert.ui.activity;

import android.support.v4.app.Fragment;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alert.base.BaseRequestListener;
import com.alert.module.ApiModule;
import com.alert.ui.fragment.ManagerFragment;
import com.alert.ui.fragment.MessageFragment;
import com.alert.ui.fragment.ProfileFragment;
import com.rctd.platfrom.rcpingan.R;

import base.core.http.response.HttpError;

/**
 * Created by zhaotao on 2017/12/27.
 */

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private Fragment message = new MessageFragment();
    private Fragment manager = new ManagerFragment();
    private Fragment profile = new ProfileFragment();

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityCreate() {
        displayFragment(R.id.fragments, message);
        ((RadioGroup) findViewById(R.id.tab)).setOnCheckedChangeListener(this);

        ApiModule.检查是否升级(new 检查升级回调(this));
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.message:
                displayFragment(R.id.fragments, message);
                break;
            case R.id.manager:
                displayFragment(R.id.fragments, manager);
                break;
            case R.id.profile:
                displayFragment(R.id.fragments, profile);
                break;
        }
    }

    private static class 检查升级回调 extends BaseRequestListener<MainActivity> {

        private 检查升级回调(MainActivity activity) {
            super(activity);
        }

        @Override
        protected void onSuccess(MainActivity parent, String data) {

        }

        @Override
        protected void onFailed(MainActivity parent, HttpError error) {

        }
    }
}
