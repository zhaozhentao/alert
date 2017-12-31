package com.wanico.app.ui.fragment;

import android.view.LayoutInflater;
import android.widget.ListView;

import com.wanico.app.R;
import com.wanico.app.ui.adapter.MessageAdapter;

import java.util.ArrayList;

/**
 * Created by zhaotao on 2017/12/27.
 */

public class MessageFragment extends BaseFragment {

    private MessageAdapter adapter;
    private ArrayList<String> data;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void onCreateView(LayoutInflater inflater) {
        data = new ArrayList<>();
        data.add("1");
        data.add("1");
        data.add("1");
        adapter = new MessageAdapter(getActivity(), data);

        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
