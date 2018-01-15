package com.alert.ui.activity;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alert.base.BaseRequestListener;
import com.alert.entity.Tracks;
import com.alert.module.ApiModule;
import com.alert.utils.LocationUtils;
import com.alert.utils.MarkerManager;
import com.alert.utils.TrackLineManager;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rctd.platfrom.rcpingan.R;

import base.core.http.response.HttpError;

/**
 * Created by zhaotao on 2018/1/1.
 */

public class DriverQueryActivity extends BaseActivity implements View.OnClickListener {

    private MapView mapView;
    private BaiduMap baiduMap;
    private LocationUtils utils;
    private MarkerManager markerManager;
    private boolean isFirstLoc = true; // 是否首次定位

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
        baiduMap.setMyLocationEnabled(true);

        utils = new LocationUtils(this, location -> {
            if (location == null || baiduMap == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder().accuracy(location.getRadius()).direction(0)
                .latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();
            baiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().target(ll).zoom(18.0f).build()));
            }
        });
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
        utils.stop();
        baiduMap.setMyLocationEnabled(false);
        mapView.onDestroy();
        mapView = null;
        if (markerManager != null) {
            markerManager.recycle();
        }
    }

    private class DialogController {
        private DialogController(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_car_driver_query, null, false);

            AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .show();

            view.findViewById(R.id.query).setOnClickListener(v -> {
                String vehsId = ((TextView) view.findViewById(R.id.vehsId)).getText().toString();
                String keyword = ((TextView) view.findViewById(R.id.keyword)).getText().toString();
                String beginTime = ((TextView) view.findViewById(R.id.beginTime)).getText().toString();
                String endTime = ((TextView) view.findViewById(R.id.endTime)).getText().toString();
                ApiModule.用户行车轨迹(vehsId, keyword, beginTime, endTime, new 轨迹查询回调(DriverQueryActivity.this));
                dialog.dismiss();
            });
        }
    }

    private static class 轨迹查询回调 extends BaseRequestListener<DriverQueryActivity> {

        private 轨迹查询回调(DriverQueryActivity activity) {
            super(activity);
        }

        @Override
        protected void onSuccess(DriverQueryActivity parent, String data) {
            Tracks tracks = new Gson().fromJson(data, new TypeToken<Tracks>() {
            }.getType());
            if (tracks.vehsTracks.size() == 0) {
                Toast.makeText(parent, "没有行车记录", Toast.LENGTH_SHORT).show();
            }
            if (parent.markerManager != null) {
                parent.markerManager.recycle();
            }
            parent.baiduMap.clear();
            parent.markerManager = new MarkerManager(tracks.vehsTracks, parent.baiduMap, parent);
            new TrackLineManager(tracks.vehsTracks, parent.baiduMap);
        }

        @Override
        protected void onFailed(DriverQueryActivity parent, HttpError error) {
            Toast.makeText(parent, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
