package com.alert.ui.fragment;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alert.base.BaseRequestListener;
import com.alert.entity.Ad;
import com.alert.module.ApiModule;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rctd.platfrom.rcpingan.R;

import java.util.ArrayList;

import base.core.http.response.HttpError;
import base.core.image.GImageLoader;

/**
 * Created by zhaotao on 2017/12/27.
 */

public class ManagerFragment extends BaseFragment implements View.OnClickListener {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_manager;
    }

    @Override
    protected void onCreateView(LayoutInflater inflater) {
        setOnClickListeners(new int[]{R.id.car_query, R.id.driver_query, R.id.add_duka, R.id.duka_manager}, this);

        ApiModule.获取广告信息("0794", new 获取广告回调(this));
    }

    private void 显示广告内容(ArrayList<Ad> ads) {
        ViewPager pager = findViewById(R.id.pager);
        Adapter adapter = new Adapter(ads);
        pager.setAdapter(adapter);

        LinearLayout dotsContainer = findViewById(R.id.dots);
        dotsContainer.removeAllViews();
        View[] dots = new View[adapter.getCount()];
        for (int i = 0; i < adapter.getCount(); i++) {
            dots[i] = getLayoutInflater().inflate(R.layout.dot, dotsContainer, true);
            dots[i] = ((LinearLayout) dots[i]).getChildAt(i);
            dots[i].setEnabled(i == 0);
        }

        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dots.length; i++) {
                    dots[i].setEnabled(i == position);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.car_query: {
                startActivity(new Intent(getActivity(), CarQueryActivity.class));
                break;
            }
            case R.id.driver_query: {
                startActivity(new Intent(getActivity(), DriverQueryActivity.class));
                break;
            }
            case R.id.add_duka: {
                startActivity(new Intent(getActivity(), AddDukaActivity.class));
                break;
            }
            case R.id.duka_manager: {
                startActivity(new Intent(getActivity(), DukaManagerActivity.class));
                break;
            }
        }
    }

    private class Adapter extends PagerAdapter {

        private View[] views;

        private Adapter(ArrayList<Ad> ads) {
            views = new View[ads.size()];

            for (int i = 0; i < views.length; i++) {
                views[i] = getLayoutInflater().inflate(R.layout.pager_ad, null, false);

                Ad ad = ads.get(i);
                View root = views[i];
                GImageLoader.getInstance().displayImage(ad.fullTitlePicPath, (ImageView) root.findViewById(R.id.cover));
            }
        }

        @Override
        public int getCount() {
            return views.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views[position]);
            return views[position];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    private static class 获取广告回调 extends BaseRequestListener<ManagerFragment> {

        private 获取广告回调(ManagerFragment activity) {
            super(activity);
        }

        @Override
        protected void onSuccess(ManagerFragment parent, String data) {
            ArrayList<Ad> ads = new Gson().fromJson(data, new TypeToken<ArrayList<Ad>>() {
            }.getType());
            parent.显示广告内容(ads);
        }

        @Override
        protected void onFailed(ManagerFragment parent, HttpError error) {
            Toast.makeText(parent.getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
