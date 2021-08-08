package com.example.aopdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Region;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.aopdemo.aop.ClickAction;
import com.example.aopdemo.aop.LoginCheck;

public class AopActivity extends AppCompatActivity {
    private View btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aop);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 查看反编译后的代码，发现对OnClickListener进行了编译期织入，即加上了AspectUtil里的防抖逻辑
                Log.e("WWS", "click btnLogin");
            }
        });
    }

    @ClickAction(name = "登录")
    public void login(View view) {
        Log.i(Constants.TAG, "login");
        startActivity(new Intent(this, LoginActivity.class));
    }

    @ClickAction(name = "转账")
    @LoginCheck
    public void withdraw(View view) {
        Log.i(Constants.TAG, "withdraw");
        startActivity(new Intent(this, OtherActivity.class));
    }

    @ClickAction(name = "优惠券")
    @LoginCheck
    public void coupon(View view) {
        Log.i(Constants.TAG, "coupon");
        startActivity(new Intent(this, OtherActivity.class));
    }
}