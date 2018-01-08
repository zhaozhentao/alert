package com.alert.ui.fragment;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.rctd.platfrom.rcpingan.R;

import java.util.ArrayList;

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

        显示广告内容();
    }

    private void 显示广告内容() {
        ArrayList<String> sd = new ArrayList<>();
        sd.add("");
        sd.add("");
        sd.add("");

        ViewPager pager = findViewById(R.id.pager);
        Adapter adapter = new Adapter(sd);
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

        private Adapter(ArrayList<String> ads) {
            views = new View[ads.size()];

            for (int i = 0; i < views.length; i++) {
                views[i] = getLayoutInflater().inflate(R.layout.pager_ad, null, false);

                View root = views[i];
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
}
