package com.wanico.app.ui.fragment;

import android.preference.EditTextPreference;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wanico.app.R;
import com.wanico.app.ui.activity.BaseActivity;

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
        填充页面信息();
    }

    private void 填充页面信息() {
        ((TextView) findViewById(R.id.rfid)).setText("rfid区位码");
        ((TextView) findViewById(R.id.name)).setText("车主姓名");
        ((TextView) findViewById(R.id.phone)).setText("车主手机号");
        ((TextView) findViewById(R.id.home_phone)).setText("家庭电话:");
        ((TextView) findViewById(R.id.id_card)).setText("身份证:");
        ((TextView) findViewById(R.id.id_address)).setText("身份证地址:");
        ((TextView) findViewById(R.id.address)).setText("家庭地址:");
        ((TextView) findViewById(R.id.rfid_num)).setText("rfid号:");
        ((TextView) findViewById(R.id.car_num)).setText("车牌:");
        ((TextView) findViewById(R.id.car_type)).setText("车型号:");
        ((TextView) findViewById(R.id.car_color)).setText("车颜色:");
        ((TextView) findViewById(R.id.car_brand)).setText("电动车品牌:");
        ((TextView) findViewById(R.id.dianji_num)).setText("电机号:");
        ((TextView) findViewById(R.id.chejiahao)).setText("车架号:");
        ((TextView) findViewById(R.id.dianliang)).setText("rfid电量:");
        ((TextView) findViewById(R.id.buy_date)).setText("购买日期:");
        ((TextView) findViewById(R.id.anzhuang_time)).setText("安装日期:");
        ((TextView) findViewById(R.id.buy_address)).setText("购买地址:");
        ((TextView) findViewById(R.id.buy_price)).setText("购买价格");
        ((TextView) findViewById(R.id.depart)).setText("登记部门");
        ((TextView) findViewById(R.id.dengjiren)).setText("登记人");
        ((TextView) findViewById(R.id.dengjiren)).setText("登记人");

        ((EditText) findViewById(R.id.remark)).setText("备注");
    }

    private void 查询() {
        String id = ((EditText) findViewById(R.id.id)).getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.query:
                查询();
                break;
        }
    }
}
