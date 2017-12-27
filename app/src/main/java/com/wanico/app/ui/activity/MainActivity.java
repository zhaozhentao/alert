package com.wanico.app.ui.activity;

import android.support.v4.app.Fragment;
import android.widget.RadioGroup;

import com.wanico.app.R;
import com.wanico.app.ui.fragment.ManagerFragment;
import com.wanico.app.ui.fragment.MessageFragment;
import com.wanico.app.ui.fragment.ProfileFragment;

/**
 * Created by zhaotao on 2017/12/27.
 */

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private Fragment message = new MessageFragment();
    private Fragment manager = new ManagerFragment();
    private Fragment profile = new ProfileFragment();

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityCreate() {
        displayFragment(R.id.fragments, message);
        ((RadioGroup) findViewById(R.id.tab)).setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.message:
                displayFragment(R.id.fragments, message);
                break;
            case R.id.manager:
                displayFragment(R.id.fragments, manager);
                break;
            case R.id.profile:
                displayFragment(R.id.fragments, profile);
                break;
        }
    }
}
