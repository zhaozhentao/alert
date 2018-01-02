package com.wanico.app.ui.fragment;

import android.view.View;

import com.wanico.app.R;
import com.wanico.app.ui.activity.BaseActivity;

/**
 * Created by zhaotao on 2018/1/1.
 */

public class AddDukaActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.add_duka_activity;
    }

    @Override
    protected void onActivityCreate() {
        setOnClickListeners(new int[]{R.id.get_location}, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_location:
                break;
        }
    }
}
