package com.example.learndemo;

import android.app.Application;

public class CustomApplication extends Application {

    private static CustomApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static Application getInstance(){
        return sInstance;
    }
}
