package com.alert.service;

import android.content.Context;
import android.util.Log;

import com.alert.App;
import com.alert.model.Notice;
import com.alert.ui.fragment.MessageFragment;
import com.alert.utils.MediaManager;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.rctd.platfrom.rcpingan.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import de.greenrobot.event.EventBus;

public class PushReceiveService extends GTIntentService {

    public PushReceiveService() {

    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {

    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        String json = new String(msg.getPayload());
        Log.e("push content", json);
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject order = jsonObject.getJSONObject("order");
            String code = order.getString("code");
            String data = order.getString("data");
            switch (code) {
                case "003":
                    消息推送(data);
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void 消息推送(String data) throws JSONException {
        JSONObject jsonObject = new JSONObject(data);
        App.getInstance().getDaoSession().getNoticeDao()
            .insert(new Notice(null, jsonObject.getString("message"), new Date()));
        EventBus.getDefault().post(new MessageFragment.Event());
        MediaManager.play(R.raw.num0);
    }

    @Override
    public void onReceiveClientId(Context context, String clientid) {

    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {

    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {

    }
}
