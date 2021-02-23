package com.example.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.aidldemo.aidl.BaseManualBinder;

public class ManualService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ManualBinder();
    }

    public class ManualBinder extends BaseManualBinder {

        @Override
        public void addBook(long id, String name) throws RemoteException {
            Log.e(getClass().getSimpleName(), "id = " + id + " name = " + name);
        }
    }
}
