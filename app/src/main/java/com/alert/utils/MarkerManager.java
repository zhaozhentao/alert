package com.alert.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alert.entity.Tracks;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.rctd.platfrom.rcpingan.R;

import java.util.ArrayList;
import java.util.List;

import base.AppManager;

/**
 * Created by zhaotao on 2017/8/17.
 */

public class MarkerManager implements BaiduMap.OnMarkerClickListener {

    private ArrayList<BitmapDescriptor> descriptors = new ArrayList<>();
    private BaiduMap baiduMap;
    private List<Tracks.VehsTracksBean> passedLatLng;

    public MarkerManager(List<Tracks.VehsTracksBean> passedLatLng, BaiduMap baiduMap, Context context) {
        this.baiduMap = baiduMap;
        this.passedLatLng = passedLatLng;

        if (passedLatLng != null && passedLatLng.size() > 0) {
            ArrayList<OverlayOptions> overlayOptionses = new ArrayList<>();

            //起点markder
            BitmapDescriptor start = BitmapDescriptorFactory.fromAssetWithDpi("Icon_start.png");
            descriptors.add(start);
            Bundle bundle = new Bundle();
            bundle.putInt("index", 0);
            Tracks.VehsTracksBean position0 = passedLatLng.get(0);
            overlayOptionses.add((new MarkerOptions()).extraInfo(bundle).position(new LatLng(position0.latitude, position0.longitude)).icon(start).zIndex(10));

            BitmapDescriptor descriptor;
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            for (int i = 1; i < passedLatLng.size() - 1; i++) {
                bundle = new Bundle();
                bundle.putInt("index", i);
                descriptor = BitmapDescriptorFactory.fromView(getCountIconView(i, inflater));
                Tracks.VehsTracksBean tracksBean = passedLatLng.get(i);
                overlayOptionses.add(new MarkerOptions().icon(descriptor).extraInfo(bundle).title("").position(new LatLng(tracksBean.latitude, tracksBean.longitude)));
                descriptors.add(descriptor);
            }
            //终点marker
            BitmapDescriptor end = BitmapDescriptorFactory.fromAssetWithDpi("Icon_end.png");
            descriptors.add(end);
            bundle = new Bundle();
            bundle.putInt("index", passedLatLng.size() - 1);
            Tracks.VehsTracksBean tracksBean = passedLatLng.get(passedLatLng.size() - 1);
            overlayOptionses.add((new MarkerOptions()).extraInfo(bundle).position(new LatLng(tracksBean.latitude, tracksBean.longitude)).icon(end).zIndex(10));

            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            List<Overlay> overlays = baiduMap.addOverlays(overlayOptionses);
            for (Overlay overlay : overlays) {
                // 只按marker 缩放
                if (overlay instanceof Marker) {
                    builder.include(((Marker) overlay).getPosition());
                }
            }
            baiduMap.setMapStatus(MapStatusUpdateFactory.newLatLngBounds(builder.build()));
        }
        baiduMap.setOnMarkerClickListener(this);
    }

    private View getCountIconView(int remainder, LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.count_icon_view, null);
        ((TextView) view.findViewById(R.id.tv_count)).setText(remainder + "");
        return view;
    }

    public void recycle() {
        if (descriptors.size() <= 0) {
            return;
        }

        for (int i = 0; i < descriptors.size(); i++) {
            BitmapDescriptor descriptor = descriptors.get(i);
            descriptor.recycle();
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        int index = marker.getExtraInfo().getInt("index");
        if (this.passedLatLng.size() <= index) {
            return false;
        }

        Activity activity = AppManager.getInstance().currentActivity();
        Tracks.VehsTracksBean track = passedLatLng.get(index);

        TextView popupText = new TextView(activity);
        popupText.setBackgroundResource(R.mipmap.popup);
        popupText.setTextColor(0xFF000000);

        String time = track.trackDateTime;
        if (time != null && time.length() > "YYYY-MM-DD".length()) {
            time = time.substring("YYYY-MM-DD".length());
        }
        popupText.setText("  序号:" + (index + 1) + " 时间:" + time + " \n " + " 地址:" + track.address + "\n");
        Tracks.VehsTracksBean clickPos = passedLatLng.get(index);
        baiduMap.showInfoWindow(new InfoWindow(popupText, new LatLng(clickPos.latitude, clickPos.longitude), -80));
        return false;
    }
}
