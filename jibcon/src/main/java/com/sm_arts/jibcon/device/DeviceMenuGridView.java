package com.sm_arts.jibcon.device;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * Created by admin on 2016-11-12.
 */
public class DeviceMenuGridView extends GridView {
    boolean mExpanded = false;
    public DeviceMenuGridView(Context context) {
        super(context);
    }

    public DeviceMenuGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DeviceMenuGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isExpanded() {
        return mExpanded;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, expandSpec);
        ViewGroup.LayoutParams param = getLayoutParams();
        param.height = getMeasuredHeight();
    }

    public void setExpanded(boolean expanded) {
        this.mExpanded = expanded;
    }
}
