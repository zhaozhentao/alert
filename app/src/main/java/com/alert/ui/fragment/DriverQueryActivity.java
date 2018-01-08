package com.alert.ui.fragment;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.alert.ui.activity.BaseActivity;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.rctd.platfrom.rcpingan.R;

/**
 * Created by zhaotao on 2018/1/1.
 */

public class DriverQueryActivity extends BaseActivity implements View.OnClickListener, BaiduMap.OnMapClickListener {

    private MapView mapView;
    private BaiduMap baiduMap;
    private LocationClient mLocClient;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.driver_query_activity;
    }

    @Override
    protected void onActivityCreate() {
        setOnClickListeners(new int[]{R.id.query}, this);

        初始化百度地图();
    }

    private void 初始化百度地图() {
        mapView = findViewById(R.id.map);
        baiduMap = mapView.getMap();
        // 开启定位图层
        baiduMap.setMyLocationEnabled(true);
        baiduMap.setOnMapClickListener(this);

        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(listener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();
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

    //    百度地图
    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }

    private int mCurrentDirection = 0;
    private boolean isFirstLoc = true; // 是否首次定位
    private MyLocationListener listener = new MyLocationListener();

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || baiduMap == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(mCurrentDirection).latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();
            baiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                    location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }
    }
    //    百度地图

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocClient.stop();
        baiduMap.setMyLocationEnabled(false);
        mapView.onDestroy();
        mapView = null;
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
