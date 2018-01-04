package com.alert.utils;

import android.view.View;

/**
 * Created by zhaotao on 2018/1/2.
 */

public class ViewHelper {

    public static void setOnClickListeners(View root, int[] ids, View.OnClickListener listener) {
        for (int id : ids) {
            root.findViewById(id).setOnClickListener(listener);
        }
    }
}
