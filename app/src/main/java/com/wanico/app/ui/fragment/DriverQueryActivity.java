package com.wanico.app.ui.fragment;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.wanico.app.R;
import com.wanico.app.ui.activity.BaseActivity;

/**
 * Created by zhaotao on 2018/1/1.
 */

public class DriverQueryActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.driver_query_activity;
    }

    @Override
    protected void onActivityCreate() {
        setOnClickListeners(new int[]{R.id.query}, this);
    }

    private void 条件搜索弹窗() {
        new DialogController(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.query:
                条件搜索弹窗();
                break;
        }
    }

    private class DialogController {

        private DialogController(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_car_driver_query, null, false);

            AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .show();

            view.findViewById(R.id.query).setOnClickListener(v -> dialog.dismiss());
        }
    }
}
