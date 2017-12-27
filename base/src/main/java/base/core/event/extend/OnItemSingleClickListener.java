package base.core.event.extend;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

/**
 * 单击事件
 * <p/>
 * Created by zzt on 7/21/15.
 */
public abstract class OnItemSingleClickListener implements AdapterView.OnItemClickListener{
    private static final String TAG = OnItemSingleClickListener.class.getName();
    private static final long DOUBLE_PRESS_INTERVAL = 1000; // in millis
    private long lastPressTime;

    public abstract void onSingleClick(AdapterView<?> parent, View view, int position, long id);


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        long pressTime = System.currentTimeMillis();
        if (pressTime - lastPressTime >= DOUBLE_PRESS_INTERVAL) {
            onSingleClick(parent, view, position, id);
        } else {
            Log.d(TAG, "double click");
        }
        lastPressTime = pressTime;
    }

}
