package com.example.learndemo;

import android.app.Application;

import com.example.learndemo.exception.CustomUncaughtExceptionHandler;

public class CustomApplication extends Application {

    private static CustomApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        new CustomUncaughtExceptionHandler(this);
    }

    public static Application getInstance(){
        return sInstance;
    }
}
