package com.alert.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alert.base.BaseRequestListener;
import com.alert.entity.CardReader;
import com.alert.entity.CardReaderEntity;
import com.alert.module.ApiModule;
import com.alert.utils.LocationUtils;
import com.rctd.platfrom.rcpingan.R;

import base.core.http.response.HttpError;

/**
 * Created by zhaotao on 2018/1/1.
 */

public class AddDukaActivity extends BaseActivity implements View.OnClickListener {

    private LocationUtils utils;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.add_duka_activity;
    }

    @Override
    protected void onActivityCreate() {
        setOnClickListeners(new int[]{R.id.get_location, R.id.save}, this);

        修改安装点();
    }

    private void 修改安装点() {
        Bundle bundle = getIntent().getBundleExtra("bundle");
        if (bundle == null) {
            return;
        }

        CardReaderEntity entity = (CardReaderEntity) bundle.get("reader");

        ((EditText) findViewById(R.id.province)).setText(entity.privince);
        ((EditText) findViewById(R.id.city)).setText(entity.city);
        ((EditText) findViewById(R.id.address)).setText(entity.address);
        ((EditText) findViewById(R.id.cardReaderNumber)).setText(entity.cardReaderNumber);
        ((EditText) findViewById(R.id.cardReaderAreaCode)).setText(entity.cardReaderAreaCode);
        ((EditText) findViewById(R.id.managerUserId)).setText(entity.managerUserId);
    }

    private void 保存() {
        Bundle bundle = getIntent().getBundleExtra("bundle");

        CardReader reader = new CardReader();
        reader.privince = ((EditText) findViewById(R.id.province)).getText().toString();
        reader.city = ((EditText) findViewById(R.id.city)).getText().toString();
        reader.area = ((EditText) findViewById(R.id.area)).getText().toString();
        reader.town = ((EditText) findViewById(R.id.town)).getText().toString();
        reader.address = ((EditText) findViewById(R.id.address)).getText().toString();
        reader.longitude = ((EditText) findViewById(R.id.longitude)).getText().toString();
        reader.latitude = ((EditText) findViewById(R.id.latitude)).getText().toString();
        reader.cardReaderNumber = ((EditText) findViewById(R.id.cardReaderNumber)).getText().toString();
        reader.cardReaderAreaCode = ((EditText) findViewById(R.id.cardReaderAreaCode)).getText().toString();
        reader.cardReaderId = ((EditText) findViewById(R.id.cardReaderId)).getText().toString();
        reader.managerUserId = ((EditText) findViewById(R.id.managerUserId)).getText().toString();
        reader.department = ((EditText) findViewById(R.id.department)).getText().toString();
        reader.remark = ((EditText) findViewById(R.id.remark)).getText().toString();
        if (null == bundle) {
            ApiModule.新增读卡器(reader, new 新增读卡器回调(this));
        } else {
            reader.cardId = ((CardReaderEntity) bundle.get("reader")).cardId;
            ApiModule.修改读卡器(reader, new 新增读卡器回调(this));
        }

    }

    private void 获取位置() {
        if (utils != null) {
            utils.stop();
        }

        utils = new LocationUtils(this, bdLocation -> {
            ((EditText) findViewById(R.id.province)).setText(bdLocation.getProvince());
            ((EditText) findViewById(R.id.city)).setText(bdLocation.getCity());
            ((EditText) findViewById(R.id.area)).setText(bdLocation.getDistrict());
            ((EditText) findViewById(R.id.address)).setText(bdLocation.getAddress().address);
            ((EditText) findViewById(R.id.longitude)).setText(bdLocation.getLongitude() + "");
            ((EditText) findViewById(R.id.latitude)).setText(bdLocation.getLatitude() + "");
            utils.stop();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (utils != null) {
            utils.stop();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_location:
                获取位置();
                break;
            case R.id.save:
                保存();
                break;
        }
    }

    private static class 新增读卡器回调 extends BaseRequestListener<AddDukaActivity> {

        private 新增读卡器回调(AddDukaActivity activity) {
            super(activity);
        }

        @Override
        protected void onSuccess(AddDukaActivity parent, String data) {
            Toast.makeText(parent, "成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onFailed(AddDukaActivity parent, HttpError error) {
            Toast.makeText(parent, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
