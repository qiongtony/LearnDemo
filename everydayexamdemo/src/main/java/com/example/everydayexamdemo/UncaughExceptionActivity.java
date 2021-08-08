package com.example.everydayexamdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import com.example.everydayexamdemo.databinding.ActivityUncaughExceptionBinding;

public class UncaughExceptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUncaughExceptionBinding binding = ActivityUncaughExceptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnThrowMainThreadException.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = null;
                str.length();
            }
        });

        binding.btnThrowThreadException.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        String str = null;
                        str.length();
                    }
                }.start();
            }
        });
        binding.btnCatchMainThreadException.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler(getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        while (true){
                            try {
                                Looper.loop();
                            }catch (Throwable e){
                                Log.e("WWS", "btnCatchMainThreadException e = " + e.getMessage());
                            }
                        }
                    }
                });
            }
        });
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
                Log.e("WWS", "uncaughtException thread = " + t.getName() + " e = " + e.getMessage());
            }
        });
    }
}