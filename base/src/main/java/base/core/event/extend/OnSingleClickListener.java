package base.core.event.extend;

import android.util.Log;
import android.view.View;

/**
 * 单击事件
 * <p/>
 * Created by tony on 9/25/14.
 */
public abstract class OnSingleClickListener implements View.OnClickListener {
    private static final String TAG = OnSingleClickListener.class.getName();
    private static final long DOUBLE_PRESS_INTERVAL = 1000; // in millis
    private long lastPressTime;

    public abstract void onSingleClick(View v);

    @Override
    public void onClick(View v) {
        long pressTime = System.currentTimeMillis();
        if (pressTime - lastPressTime >= DOUBLE_PRESS_INTERVAL) {
            onSingleClick(v);
        } else {
            Log.d(TAG, "double click");
        }
        lastPressTime = pressTime;
    }
}
