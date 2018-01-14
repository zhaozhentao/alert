package com.alert.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alert.base.BaseRequestListener;
import com.alert.entity.RFIDQueryResult;
import com.alert.module.ApiModule;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rctd.platfrom.rcpingan.R;

import base.core.http.response.HttpError;

/**
 * Created by zhaotao on 2018/1/1.
 */

public class CarQueryActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.car_query_activity;
    }

    @Override
    protected void onActivityCreate() {
        setOnClickListeners(new int[]{R.id.query}, this);
    }

    private void 填充页面信息(RFIDQueryResult result) {
        ((TextView) findViewById(R.id.rfid)).setText("rfid区位码" + result.rfidAreaCode);
        ((TextView) findViewById(R.id.mobilePhone)).setText("车主姓名:" + result.idName);
        ((TextView) findViewById(R.id.phone)).setText("车主手机号:" + result.mobilePhone);
        ((TextView) findViewById(R.id.home_phone)).setText("家庭电话:" + result.homePhone);
        ((TextView) findViewById(R.id.id_card)).setText("身份证:" + result.idCardNumber);
        ((TextView) findViewById(R.id.id_address)).setText("身份证地址:" + result.idAddress);
        ((TextView) findViewById(R.id.address)).setText("家庭地址:" + result.homeAdddress);
        ((TextView) findViewById(R.id.rfid_num)).setText("rfid号:" + result.rfidNumber);
        ((TextView) findViewById(R.id.car_num)).setText("车牌:" + result.vehsNumber);
        ((TextView) findViewById(R.id.car_type)).setText("车型号:" + result.vehsModel);
        ((TextView) findViewById(R.id.car_color)).setText("车颜色:" + result.vehsColor);
        ((TextView) findViewById(R.id.car_brand)).setText("电动车品牌:" + result.vehsBrand);
        ((TextView) findViewById(R.id.dianji_num)).setText("电机号:" + result.vehsMotoNumber);
        ((TextView) findViewById(R.id.chejiahao)).setText("车架号:" + result.vehsArchNumber);
        ((TextView) findViewById(R.id.dianliang)).setText("rfid电量:" + result.rfidStatus);
        ((TextView) findViewById(R.id.buy_date)).setText("购买日期:" + result.buyDate);
        ((TextView) findViewById(R.id.anzhuang_time)).setText("安装日期:" + result.installDate);
        ((TextView) findViewById(R.id.buy_address)).setText("购买地址:" + result.buyAddress);
        ((TextView) findViewById(R.id.buy_price)).setText("购买价格" + result.price);
        ((TextView) findViewById(R.id.depart)).setText("登记部门" + result.rfidRegistDepartment);
        ((TextView) findViewById(R.id.dengjiren)).setText("登记人" + result.rfidRegistrant);

        ((EditText) findViewById(R.id.remark)).setText("备注" + result.remark);
    }

    private void 查询() {
        String id = ((EditText) findViewById(R.id.id)).getText().toString();
        ApiModule.通过车牌号或RFID号获取车辆信息("abc8989898xxdfe", new 查询回调(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.query:
                查询();
                break;
        }
    }

    private static class 查询回调 extends BaseRequestListener<CarQueryActivity> {

        private 查询回调(CarQueryActivity activity) {
            super(activity);
        }

        @Override
        protected void onSuccess(CarQueryActivity parent, String data) {
            RFIDQueryResult result = new Gson().fromJson(data, new TypeToken<RFIDQueryResult>() {
            }.getType());
            parent.填充页面信息(result);
        }

        @Override
        protected void onFailed(CarQueryActivity parent, HttpError error) {
            Toast.makeText(parent, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
