package com.example.learndemo;


import android.os.Bundle;

import com.example.learndemo.base.BaseActivity;
import com.example.learndemo.databinding.ActivityJniBinding;

public class JniActivity extends BaseActivity<ActivityJniBinding> {
    {
        System.loadLibrary("hello-jni");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.btnShow.setText("value = " + nativeTest());
    }

    public static native int  nativeTest();

    @Override
    public int getLayoutResId() {
        return R.layout.activity_jni;
    }
}