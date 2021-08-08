package com.example.learndemo.hook;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HookHelper {

    public void hook(Activity activity, HookTouchListener.TouchCallback touchCallback) {
        ViewGroup vg = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        hookView(vg, touchCallback);
    }

    private void hookView(View view, HookTouchListener.TouchCallback touchCallback) {
        if (view instanceof ViewGroup) {
            hookViewTouchListener(view, touchCallback);
            ViewGroup vg = (ViewGroup) view;
            for (int i = 0; i < vg.getChildCount(); i++) {
                hookView(vg.getChildAt(i), touchCallback);
            }
        } else {
            hookViewTouchListener(view, touchCallback);
        }
    }


    public void hookViewTouchListener(View view, HookTouchListener.TouchCallback touchCallback) {
        try {
            // 不用view获取class，是因为view可能是view or view的子类
            Class<? extends View> cls = View.class;
            Method method = cls.getDeclaredMethod("getListenerInfo");
            method.setAccessible(true);
            Object listenerInfo = method.invoke(view, null);
            Class<?> listenerInfoCls = Class.forName("android.view.View$ListenerInfo");
            Field onTouchListenerField = listenerInfoCls.getDeclaredField("mOnTouchListener");
            onTouchListenerField.setAccessible(true);
            View.OnTouchListener onTouchListener = (View.OnTouchListener) onTouchListenerField.get(listenerInfo);
            if (onTouchListener instanceof HookTouchListener){
                return;
            }
            if (onTouchListener == null) {
                onTouchListener = new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                };
            }
            HookTouchListener touchListener = new HookTouchListener(touchCallback, onTouchListener);
            onTouchListenerField.set(listenerInfo, touchListener);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | NoSuchFieldException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
