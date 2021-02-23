package com.example.aidldemo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aidldemo.aidl.BaseManualBinder;
import com.example.aidldemo.aidl.IManualAidlInterface;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            mBinder = (BookService.MyBinder) service;
            mBinder = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBinder = null;
        }
    };
//    private BookService.MyBinder mBinder;

    private IMyAidlInterface mBinder;

    private IManualAidlInterface mManualAidlInterface;

    private ServiceConnection mManualSC = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mManualAidlInterface = BaseManualBinder.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mManualAidlInterface = null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindService(new Intent(this, BookService.class), mServiceConnection, Service.BIND_AUTO_CREATE);
        bindService(new Intent(this, ManualService.class), mManualSC, Service.BIND_AUTO_CREATE);
        View btnExecute = findViewById(R.id.btnExecute);
        View btnManualExecute = findViewById(R.id.btnManualExecute);
        btnExecute.setOnClickListener(v -> {
                if (mBinder == null){
                    Toast.makeText(MainActivity.this, "binder为空", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        mBinder.execute();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "执行异常", Toast.LENGTH_SHORT).show();
                    }
                }
        });

        btnManualExecute.setOnClickListener(v -> {
            if (mBinder == null){
                Toast.makeText(MainActivity.this, "binder为空", Toast.LENGTH_SHORT).show();
            }else{
                try {
                    mManualAidlInterface.addBook(99L, "《何为美好生活》");
                } catch (RemoteException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "执行异常", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
