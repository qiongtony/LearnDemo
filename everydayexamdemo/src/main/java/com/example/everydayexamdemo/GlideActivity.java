package com.example.everydayexamdemo;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.everydayexamdemo.databinding.ActivityGlideBinding;


public class GlideActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityGlideBinding binding = ActivityGlideBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE

            }, 100);
        }
        binding.btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Glide.with(GlideActivity.this).load(R.drawable.icon_7).into(ivIcon);
                // 20m
                Glide.with(GlideActivity.this).load("https://seopic.699pic.com/photo/40011/0709.jpg_wh1200.jpg")
                        .into(binding.ivIcon);
            }
        });

        binding.btnLoadToBigIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Glide.with(GlideActivity.this).load(R.drawable.icon_7).into(ivIcon);
                // 20m
                Glide.with(GlideActivity.this).load("https://seopic.699pic.com/photo/40011/0709.jpg_wh1200.jpg")
                        .into(binding.ivBigIcon);
            }
        });

    }
}