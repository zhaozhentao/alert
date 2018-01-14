package com.alert.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alert.entity.CardReaderEntity;
import com.rctd.platfrom.rcpingan.R;
import com.alert.ui.fragment.BaseHolder;

import java.util.ArrayList;

/**
 * Created by zhaotao on 2018/1/2.
 */

public class DukaInfoAdapter extends BaseAdapter {

    private ArrayList<CardReaderEntity> data;
    private LayoutInflater inflater;

    public DukaInfoAdapter(LayoutInflater inflater, ArrayList<CardReaderEntity> data) {
        this.inflater = inflater;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;

        if (convertView == null) {
            holder = new Holder(convertView = inflater.inflate(R.layout.item_duka_info, parent, false));
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.refresh(position);
        return convertView;
    }

    private class Holder implements BaseHolder {

        private TextView 编号, 安装时间, 状态, 管理人, 手机号, 区域码, 地址;

        private Holder(View rootView) {
            编号 = rootView.findViewById(R.id.bianhao);
            安装时间 = rootView.findViewById(R.id.anzhuang_time);
            状态 = rootView.findViewById(R.id.status);
            管理人 = rootView.findViewById(R.id.guanliren);
            手机号 = rootView.findViewById(R.id.phone);
            区域码 = rootView.findViewById(R.id.quyuma);
            地址 = rootView.findViewById(R.id.address);
        }

        @Override
        public void refresh(int i) {
            CardReaderEntity cardReader = data.get(i);
            编号.setText("编号:" + cardReader.cardReaderNumber);
            安装时间.setText("安装时间");
            状态.setText("状态" + cardReader.cardReaderStatus);
            管理人.setText("管理人:" + cardReader.managerUserName);
            手机号.setText("手机号:" + cardReader.managerUserMobile);
            区域码.setText("区域码:" + cardReader.cardReaderAreaCode);
            地址.setText("地址:" + cardReader.address);
        }
    }
}
