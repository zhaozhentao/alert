package base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Wilson on 14-6-22.
 */
public class AdjustListView extends ListView {

    public AdjustListView(Context context) {
        super(context);
    }

    public AdjustListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdjustListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
