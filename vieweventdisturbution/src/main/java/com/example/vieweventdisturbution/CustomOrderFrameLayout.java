package com.example.vieweventdisturbution;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 自定义摆放顺序
 */
public class CustomOrderFrameLayout extends FrameLayout {
    public CustomOrderFrameLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public CustomOrderFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomOrderFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        // 支持自动子View顺序
        setChildrenDrawingOrderEnabled(true);
    }

    @Override
    protected void setChildrenDrawingOrderEnabled(boolean enabled) {
        super.setChildrenDrawingOrderEnabled(enabled);
    }

    // 让事件分发按0..count的顺序
    @Override
    protected int getChildDrawingOrder(int childCount, int drawingPosition) {
        Log.e("WWS", "getChildDrawingOrder childCount = " + childCount + " drawingPosition = " + drawingPosition);
        return childCount - drawingPosition - 1;
    }
}
