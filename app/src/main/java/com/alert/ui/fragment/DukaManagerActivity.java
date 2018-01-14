package com.alert.ui.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.alert.App;
import com.alert.base.BaseRequestListener;
import com.alert.entity.CardReaderEntity;
import com.alert.module.ApiModule;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rctd.platfrom.rcpingan.R;
import com.alert.ui.activity.BaseActivity;
import com.alert.ui.adapter.DukaInfoAdapter;
import com.alert.utils.ViewHelper;

import java.util.ArrayList;

import base.core.http.response.HttpError;

/**
 * Created by zhaotao on 2018/1/1.
 */

public class DukaManagerActivity extends BaseActivity implements View.OnClickListener {

    private ArrayList<CardReaderEntity> data = new ArrayList<>();
    private DukaInfoAdapter adapter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.duka_manager_activity;
    }

    @Override
    protected void onActivityCreate() {
        setOnClickListeners(new int[]{R.id.query}, this);

        显示读卡器();
    }

    private void 显示读卡器() {
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter = new DukaInfoAdapter(LayoutInflater.from(this), data));
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            new AlertDialog.Builder(DukaManagerActivity.this)
                .setItems(new String[]{"修改安装点", "解绑读卡器", "删除安装点"}, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            修改安装点(position);
                            break;
                        case 1:
                            解绑读卡器(position);
                            break;
                        case 2:
                            删除安装点(position);
                            break;
                    }
                })
                .show();
            return true;
        });
    }

    private void 修改安装点(int position) {
        CardReaderEntity entity = data.get(position);
        Intent intent = new Intent(this, AddDukaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("reader", entity);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }

    private void 解绑读卡器(int position) {
        new AlertDialog.Builder(this)
            .setMessage("确认解绑读卡器")
            .setNegativeButton("取消", null)
            .setPositiveButton("确认", (dialog, which) -> {
                CardReaderEntity entity = data.get(position);
                ApiModule.解绑读卡器(entity.cardId, entity.managerUserId, new 删除安装点回调(this));
            }).show();
    }

    private void 删除安装点(int position) {
        new AlertDialog.Builder(this)
            .setMessage("确认删除安装点")
            .setNegativeButton("取消", null)
            .setPositiveButton("确认", (dialog, which) -> ApiModule.删除安装点(data.get(position).cardId, new 删除安装点回调(this))).show();
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
            String cardReaderNumber = ((EditText) dialog.findViewById(R.id.cardReaderNumber)).getText().toString();
            String cardReaderAreaCode = ((EditText) dialog.findViewById(R.id.cardReaderAreaCode)).getText().toString();
            String cardReaderStatus = ((EditText) dialog.findViewById(R.id.cardReaderStatus)).getText().toString();
            String installStatus = ((EditText) dialog.findViewById(R.id.installStatus)).getText().toString();
            String beginTime = ((EditText) dialog.findViewById(R.id.beginTime)).getText().toString();
            String endTime = ((EditText) dialog.findViewById(R.id.endTime)).getText().toString();
            ApiModule.读卡器信息查询(cardReaderNumber, cardReaderAreaCode, cardReaderStatus, installStatus,
                beginTime, endTime, 0, new 查询回调(DukaManagerActivity.this));
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

    private static class 查询回调 extends BaseRequestListener<DukaManagerActivity> {

        private 查询回调(DukaManagerActivity activity) {
            super(activity);
        }

        @Override
        protected void onSuccess(DukaManagerActivity parent, String data) {
            ArrayList<CardReaderEntity> entities = new Gson().fromJson(data, new TypeToken<ArrayList<CardReaderEntity>>() {
            }.getType());

            parent.data.clear();
            parent.data.addAll(entities);
            parent.adapter.notifyDataSetChanged();
        }

        @Override
        protected void onFailed(DukaManagerActivity parent, HttpError error) {
            Toast.makeText(parent, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private static class 删除安装点回调 extends BaseRequestListener<DukaManagerActivity> {

        private 删除安装点回调(DukaManagerActivity activity) {
            super(activity);
        }

        @Override
        protected void onSuccess(DukaManagerActivity parent, String data) {

        }

        @Override
        protected void onFailed(DukaManagerActivity parent, HttpError error) {

        }
    }
}
