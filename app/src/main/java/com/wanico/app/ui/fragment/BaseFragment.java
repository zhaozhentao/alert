package com.wanico.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhaotao on 2017/11/24.
 */

public abstract class BaseFragment extends Fragment {

    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutResourceId(), container, false);
        onCreateView(inflater);
        return rootView;
    }

    protected abstract int getLayoutResourceId();

    protected abstract void onCreateView(LayoutInflater inflater);

    public <T extends View> T findViewById(@IdRes int id) {
        return rootView.findViewById(id);
    }
}
