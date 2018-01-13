package com.alert.ui.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alert.App;
import com.rctd.platfrom.rcpingan.R;
import com.alert.ui.activity.AboutActivity;
import com.alert.ui.activity.ResetPasswordActivity;
import com.alert.ui.activity.SettingsActivity;

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
        setOnClickListeners(new int[]{R.id.login, R.id.reset_password, R.id.bind, R.id.clear, R.id.settings, R.id.about_us, R.id.logout}, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        显示登录状态();
    }

    private void 显示登录状态() {
        App.User user = App().getUser();
        TextView mobilePhone = findViewById(R.id.mobilePhone);
        View login = findViewById(R.id.login);

        if (user != null) {
            mobilePhone.setText(user.getMobilePhone());
            mobilePhone.setVisibility(View.VISIBLE);
            login.setVisibility(View.GONE);
        } else {
            mobilePhone.setVisibility(View.GONE);
            login.setVisibility(View.VISIBLE);
        }
    }

    private void 登录() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    private void 关于我们() {
        Intent intent = new Intent(getActivity(), AboutActivity.class);
        startActivity(intent);
    }

    private void 系统设置() {
        Intent intent = new Intent(getActivity(), SettingsActivity.class);
        startActivity(intent);
    }

    private void 修改密码() {
        Intent intent = new Intent(getActivity(), ResetPasswordActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                登录();
                break;
            case R.id.reset_password:
                修改密码();
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
