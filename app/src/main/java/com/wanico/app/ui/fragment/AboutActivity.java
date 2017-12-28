package com.wanico.app.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;

import com.wanico.app.BuildConfig;
import com.wanico.app.R;
import com.wanico.app.ui.activity.BaseActivity;
import com.wanico.app.ui.dialog.CustomDialog;

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

        ((TextView) findViewById(R.id.version)).setText(BuildConfig.VERSION_NAME);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.call:
                打电话();
                break;
        }
    }

    private void 打电话() {
        CustomDialog dialog = new CustomDialog(this);
        dialog.setTitle("title");
        dialog.setContent("拨打电话 020-23328999");
        dialog.setOK("拨打", v -> {
            dialog.dismiss();
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:020-23328999"));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivity(intent);
        });
        dialog.show();
    }
}
