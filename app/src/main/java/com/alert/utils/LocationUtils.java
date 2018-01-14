package com.alert.utils;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * Created by zhaotao on 2018/1/14.
 */

public class LocationUtils {

    private LocationClient client;

    private LocationListener recLocation;

    private BDLocationListener listener = bdLocation -> {
        recLocation.onLocationReceive(bdLocation);
    };

    public LocationUtils(Context context, LocationListener recLocation) {
        client = new LocationClient(context);
        client.registerNotifyLocationListener(listener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        this.recLocation = recLocation;
        client.setLocOption(option);
        client.start();
    }

    public void stop() {
        client.stop();
        client.unRegisterLocationListener(listener);
    }

    public interface LocationListener {
        void onLocationReceive(BDLocation bdLocation);
    }
}
