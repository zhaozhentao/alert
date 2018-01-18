package com.alert.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alert.base.BaseRequestListener;
import com.alert.entity.CardReader;
import com.alert.entity.CardReaderEntity;
import com.alert.entity.Manager;
import com.alert.module.ApiModule;
import com.alert.utils.LocationUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rctd.platfrom.rcpingan.R;

import java.util.ArrayList;
import java.util.HashMap;

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
        setOnClickListeners(new int[]{R.id.get_location, R.id.save, R.id.cardReaderAreaCode, R.id.managerUserId, R.id.department}, this);

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
        ((Button) findViewById(R.id.managerUserId)).setText(entity.managerUserId);
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
        reader.cardReaderAreaCode = ((Button) findViewById(R.id.cardReaderAreaCode)).getText().toString();
        reader.cardReaderId = ((EditText) findViewById(R.id.cardReaderId)).getText().toString();
        reader.managerUserId = ((Button) findViewById(R.id.managerUserId)).getText().toString();
        reader.department = ((Button) findViewById(R.id.department)).getText().toString();
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

    private void 获取用户区域码() {
        ApiModule.获取用户区域码(new 获取区域码回调(this));
    }

    private void 获取管理人员信息() {
        ApiModule.获取管理人员信息(new 获取管理人员信息回调(this));
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
            case R.id.cardReaderAreaCode:
                获取用户区域码();
                break;
            case R.id.managerUserId:
                获取管理人员信息();
                break;
            case R.id.department:
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

    private static class 获取区域码回调 extends BaseRequestListener<AddDukaActivity> {

        private 获取区域码回调(AddDukaActivity activity) {
            super(activity);
        }

        @Override
        protected void onSuccess(AddDukaActivity parent, String data) {
            ArrayList<HashMap<String, String>> maps = new Gson().fromJson(data, new TypeToken<ArrayList<HashMap<String, String>>>() {
            }.getType());

            ArrayList<String> areas = new ArrayList<>();
            for (HashMap<String, String> a : maps) {
                areas.add(a.get("areaCode"));
            }

            String[] items = areas.toArray(new String[areas.size()]);
            new AlertDialog.Builder(parent).setItems(items, (dialog, which) -> {
                ((Button) parent.findViewById(R.id.cardReaderAreaCode)).setText(items[which]);
            }).show();
        }

        @Override
        protected void onFailed(AddDukaActivity parent, HttpError error) {
            Toast.makeText(parent, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private static class 获取管理人员信息回调 extends BaseRequestListener<AddDukaActivity> {

        private 获取管理人员信息回调(AddDukaActivity activity) {
            super(activity);
        }

        @Override
        protected void onSuccess(AddDukaActivity parent, String data) {
            ArrayList<Manager> managers = new Gson().fromJson(data, new TypeToken<ArrayList<Manager>>() {
            }.getType());

            ArrayList<String> names = new ArrayList<>();
            for (Manager a : managers) {
                names.add(a.managerMan);
            }
            String[] mansName = names.toArray(new String[names.size()]);
            new AlertDialog.Builder(parent).setItems(mansName, (dialog, which) -> {
                Button manager = parent.findViewById(R.id.managerUserId);
                manager.setText(mansName[which]);
                manager.setTag(managers.get(which));
            }).show();
        }

        @Override
        protected void onFailed(AddDukaActivity parent, HttpError error) {
            Toast.makeText(parent, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
