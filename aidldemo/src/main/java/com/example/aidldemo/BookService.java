package com.example.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class BookService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    private void print(){
        Log.e(getClass().getSimpleName(), "print");
    }

    class MyBinder extends IMyAidlInterface.Stub{
        @Override
        public void execute(){
            print();
        }
    }
}
