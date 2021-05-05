package com.example.aopdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.aopdemo.aop.ClickAction;
import com.example.aopdemo.aop.LoginCheck;

public class AopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aop);
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