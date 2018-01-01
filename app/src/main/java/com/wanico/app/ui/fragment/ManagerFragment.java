package com.wanico.app.ui.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.wanico.app.R;

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
}
