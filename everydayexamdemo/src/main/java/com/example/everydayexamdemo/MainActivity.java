package com.example.everydayexamdemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.everydayexamdemo.databinding.ActivityMainBinding;
import com.example.everydayexamdemo.designmode.charcter_11_command.CustomDrawActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnJumpToFragmentRetainPage.setOnClickListener(v -> {
            Log.e("WWS", "click btnJumpToFragmentRetainPage");
            openPage(FragmentRetainActivity.class);
        });
        binding.btnJumpToExceptionPage.setOnClickListener(v -> openPage(UncaughExceptionActivity.class));
        binding.btnJumpToGlidePage.setOnClickListener(v -> openPage(GlideActivity.class));
        binding.btnJumpToDrawViewPage.setOnClickListener(v -> openPage(CustomDrawActivity.class));

    }

    private void openPage(Class<?> clazz) {
        startActivity(new Intent(MainActivity.this, clazz));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.e("WWS", "onWindowFocusChanged");
    }
}