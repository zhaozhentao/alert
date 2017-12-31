package com.wanico.app.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanico.app.R;
import com.wanico.app.ui.fragment.BaseHolder;

import java.util.ArrayList;

/**
 * Created by zhaotao on 2017/12/31.
 */

public class MessageAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<String> data;

    public MessageAdapter(Activity activity, ArrayList<String> data) {
        inflater = LayoutInflater.from(activity);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        BaseHolder holder;
        if (view == null) {
            holder = new Holder(view = inflater.inflate(R.layout.item_message, null, false));
        } else {
            holder = (Holder) view.getTag();
        }
        holder.refresh(i);
        return view;
    }

    private class Holder implements BaseHolder {

        private ImageView avatar;
        private TextView content, time;

        private Holder(View root) {
            avatar = root.findViewById(R.id.avatar);
            content = root.findViewById(R.id.content);
            time = root.findViewById(R.id.time);
            root.setOnClickListener(v -> {
            });
        }

        @Override
        public void refresh(int i) {
            content.setText("content");
            time.setText("time");
        }
    }
}
