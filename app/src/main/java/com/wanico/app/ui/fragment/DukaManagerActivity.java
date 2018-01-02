package com.wanico.app.ui.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.wanico.app.R;
import com.wanico.app.ui.activity.BaseActivity;
import com.wanico.app.ui.adapter.DukaInfoAdapter;
import com.wanico.app.utils.ViewHelper;

import java.util.ArrayList;

/**
 * Created by zhaotao on 2018/1/1.
 */

public class DukaManagerActivity extends BaseActivity implements View.OnClickListener {

    private DukaInfoAdapter adapter;
    private ArrayList<String> data = new ArrayList<>();

    @Override
    protected int getLayoutResourceId() {
        return R.layout.duka_manager_activity;
    }

    @Override
    protected void onActivityCreate() {
        setOnClickListeners(new int[]{R.id.query}, this);

        data.add("one");
        data.add("two");

        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter = new DukaInfoAdapter(LayoutInflater.from(this), data));
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            new AlertDialog.Builder(DukaManagerActivity.this)
                .setItems(new String[]{"修改安装点", "解绑读卡器", "删除安装点"}, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                    }
                })
                .show();
            return true;
        });
    }

    private void 条件搜索() {
        new QueryDialogController(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.query:
                条件搜索();
                break;
        }
    }

    private class QueryDialogController implements View.OnClickListener {

        private AlertDialog dialog;

        private QueryDialogController(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_query, null, false);
            ViewHelper.setOnClickListeners(view, new int[]{R.id.do_query}, this);

            dialog = new AlertDialog.Builder(context)
                .setView(view)
                .setOnDismissListener(dialog -> QueryDialogController.this.dialog = null)
                .show();
        }

        private void 查询() {
            dialog.dismiss();
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.do_query:
                    查询();
                    break;
            }
        }
    }
}
