package com.wanico.app.ui.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.wanico.app.R;

/**
 * Created by zhaotao on 2017/12/27.
 */

public class ProfileFragment extends BaseFragment implements View.OnClickListener {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void onCreateView(LayoutInflater inflater) {
        setOnClickListeners(new int[]{R.id.reset_password, R.id.bind, R.id.clear, R.id.settings, R.id.about_us, R.id.logout}, this);
    }

    private void 关于我们() {
        Intent intent = new Intent(getActivity(), AboutActivity.class);
        startActivity(intent);
    }

    private void 系统设置() {
        Intent intent = new Intent(getActivity(), SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reset_password:
                break;
            case R.id.bind:
                break;
            case R.id.clear:
                break;
            case R.id.settings:
                系统设置();
                break;
            case R.id.about_us:
                关于我们();
                break;
            case R.id.logout:
                break;
        }
    }
}
