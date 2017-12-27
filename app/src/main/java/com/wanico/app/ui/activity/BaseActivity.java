package com.wanico.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.umeng.analytics.MobclickAgent;
import com.wanico.app.R;

import base.AppManager;

/**
 * Created by zhaotao on 2017/11/24.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getLayoutResourceId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        setContentView(getLayoutResourceId());
        View back = findViewById(R.id.back);
        if (back != null) {
            back.setOnClickListener(view -> onBackPressed());
        }

        onActivityCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
    }

    protected abstract void onActivityCreate();

    protected Fragment displayingFragment = null;

    protected void displayFragment(int layoutId, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (!fragment.isAdded()) {
            transaction.add(layoutId, fragment);
        }
        if (displayingFragment != null) {
            transaction.hide(displayingFragment);
        }
        displayingFragment = fragment;
        transaction.show(fragment);
        transaction.commit();
    }

    protected void setOnClickListeners(int[] ids, View.OnClickListener listener) {
        for (int id : ids) {
            findViewById(id).setOnClickListener(listener);
        }
    }
}
