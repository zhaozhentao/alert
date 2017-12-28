package com.wanico.app.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.wanico.app.R;

/**
 * Created by zhaotao on 2017/12/28.
 */

public class CustomDialog extends AlertDialog {

    private String title, ok, content, cancel;
    private View.OnClickListener okListener;
    private View.OnClickListener cancelListener;

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.custom_dialog);

        ((TextView) findViewById(R.id.title)).setText(title);

        TextView okButton = findViewById(R.id.ok);
        okButton.setText(ok);
        okButton.setOnClickListener(okListener);

        ((TextView) findViewById(R.id.content)).setText(content);

        if (cancelListener != null) {
            findViewById(R.id.cancel).setOnClickListener(cancelListener);
        } else {
            findViewById(R.id.cancel).setOnClickListener(view -> dismiss());
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOK(String ok, View.OnClickListener listener) {
        this.ok = ok;
        this.okListener = listener;
    }

    public void setCancel(String cancel, View.OnClickListener listener) {
        this.cancel = cancel;
        this.cancelListener = listener;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
