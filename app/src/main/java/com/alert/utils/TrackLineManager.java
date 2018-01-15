package com.alert.utils;

import android.widget.Toast;

import com.alert.App;
import com.alert.entity.Tracks;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;

import java.util.List;

/**
 * Created by zhaotao on 2018/1/15.
 */

public class TrackLineManager implements OnGetRoutePlanResultListener {

    private RoutePlanSearch search = null;
    private List<Tracks.VehsTracksBean> passedLatLng;
    private int index;
    private BaiduMap baiduMap;

    public TrackLineManager(List<Tracks.VehsTracksBean> passedLatLng, BaiduMap baiduMap) {
        this.passedLatLng = passedLatLng;
        this.baiduMap = baiduMap;
        search = RoutePlanSearch.newInstance();
        search.setOnGetRoutePlanResultListener(this);
        搜索两个点之间的路线();
    }

    private void 搜索两个点之间的路线() {
        if (index == passedLatLng.size() - 1) {
            return; //已经是最后一个节点了
        }

        Tracks.VehsTracksBean start = passedLatLng.get(index);
        PlanNode startNode = PlanNode.withLocation(new LatLng(start.latitude, start.longitude));
        Tracks.VehsTracksBean end = passedLatLng.get(index + 1);
        PlanNode endNode = PlanNode.withLocation(new LatLng(end.latitude, end.longitude));
        search.drivingSearch((new DrivingRoutePlanOption()).from(startNode).to(endNode));
    }

    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(App.getInstance(), "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
            return;
        }

        DrivingRouteOverlay overlay = new DrivingRouteOverlay(baiduMap);
        baiduMap.setOnMarkerClickListener(overlay);
        overlay.setData(result.getRouteLines().get(0));
        overlay.addToMap();

        index++;
        搜索两个点之间的路线();
    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

    }
}
