package com.example.test.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.test.R;

public class GlideActivity extends AppCompatActivity {
    private ImageView ivIcon;
    private Button btnLoad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ivIcon = findViewById(R.id.iv_icon);
        btnLoad = findViewById(R.id.btnLoad);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE

            }, 100);
        }
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Glide.with(GlideActivity.this).load(R.drawable.icon_7).into(ivIcon);
                // 20m
                Glide.with(GlideActivity.this).load("https://seopic.699pic.com/photo/40011/0709.jpg_wh1200.jpg")
                        .into(ivIcon);
            }
        });

    }
}