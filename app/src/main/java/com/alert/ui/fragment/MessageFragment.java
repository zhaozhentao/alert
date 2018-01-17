package com.alert.ui.fragment;

import android.view.LayoutInflater;
import android.widget.ListView;

import com.alert.model.Notice;
import com.alert.ui.adapter.MessageAdapter;
import com.rctd.platfrom.rcpingan.R;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

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
        EventBus.getDefault().register(this);

        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter = new MessageAdapter(getActivity(), data));
        加载历史消息();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private void 加载历史消息() {
        data.clear();
        List<Notice> list = getDao().getNoticeDao().queryBuilder().list();
        data.addAll(list);
        adapter.notifyDataSetChanged();

        ListView listView = findViewById(R.id.list);
        listView.smoothScrollToPositionFromTop(adapter.getCount(), 0, 0);
    }

    public static class Event {

    }

    public void onEvent(Event ev) {
        加载历史消息();
    }
}
