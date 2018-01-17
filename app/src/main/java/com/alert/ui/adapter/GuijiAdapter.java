package com.alert.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alert.entity.Tracks;
import com.alert.ui.activity.DriverQueryActivity;
import com.alert.ui.fragment.BaseHolder;
import com.rctd.platfrom.rcpingan.R;

import java.util.List;

/**
 * Created by zhaotao on 2018/1/17.
 */

public class GuijiAdapter extends BaseAdapter {

    private Activity activity;
    private List<Tracks.VehsTracksBean> vehsTracks;
    private LayoutInflater inflater;

    public GuijiAdapter(DriverQueryActivity activity, List<Tracks.VehsTracksBean> vehsTracks) {
        this.activity = activity;
        this.vehsTracks = vehsTracks;
        this.inflater = LayoutInflater.from(this.activity);
    }

    @Override
    public int getCount() {
        return this.vehsTracks.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder(convertView = inflater.inflate(R.layout.item_guiji, null, false));
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.refresh(position);
        return convertView;
    }

    private class Holder implements BaseHolder {

        private TextView 地址, 时间;

        private Holder(View view) {
            地址 = view.findViewById(R.id.address);
            时间 = view.findViewById(R.id.time);
        }

        @Override
        public void refresh(int i) {
            Tracks.VehsTracksBean a = vehsTracks.get(i);
            地址.setText(a.address);
            时间.setText(a.trackDateTime);
        }
    }
}
