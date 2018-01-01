package com.wanico.app.ui.fragment;

import android.view.LayoutInflater;
import android.widget.ListView;

import com.wanico.app.R;
import com.wanico.app.model.Notice;
import com.wanico.app.ui.adapter.MessageAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaotao on 2017/12/27.
 */

public class MessageFragment extends BaseFragment {

    private MessageAdapter adapter;
    private List<Notice> data = new ArrayList<>();

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void onCreateView(LayoutInflater inflater) {
        加载历史消息();
        adapter = new MessageAdapter(getActivity(), data);

        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
    }

    private void 加载历史消息() {
        getDao().getNoticeDao().insert(new Notice(null, "content", new Date()));
        List<Notice> list = getDao().getNoticeDao().queryBuilder().list();
        data.addAll(list);
    }
}
