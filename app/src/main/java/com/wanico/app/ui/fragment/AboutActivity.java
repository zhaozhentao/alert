package com.wanico.app.ui.fragment;

import android.os.Build;
import android.view.View;
import android.widget.TextView;

import com.wanico.app.BuildConfig;
import com.wanico.app.R;
import com.wanico.app.ui.activity.BaseActivity;

/**
 * Created by zhaotao on 2017/12/27.
 */

public class AboutActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.about_activity;
    }

    @Override
    protected void onActivityCreate() {
        setOnClickListeners(new int[]{R.id.call}, this);

        ((TextView)findViewById(R.id.version)).setText(BuildConfig.VERSION_NAME);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.call:
                break;
        }
    }
}
