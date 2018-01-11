package com.alert.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alert.model.Notice;
import com.alert.ui.fragment.BaseHolder;
import com.rctd.platfrom.rcpingan.R;

import java.util.List;

import base.utils.StringUtils;

/**
 * Created by zhaotao on 2017/12/31.
 */

public class MessageAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Notice> data;

    public MessageAdapter(Activity activity, List<Notice> data) {
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
            view.setTag(holder);
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
        }

        @Override
        public void refresh(int i) {
            Notice notice = data.get(i);

            content.setText(notice.content);
            time.setText(StringUtils.formatCn(notice.getCreated_at()));
        }
    }
}
