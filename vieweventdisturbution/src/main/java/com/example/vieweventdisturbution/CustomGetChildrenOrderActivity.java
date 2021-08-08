package com.example.vieweventdisturbution;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CustomGetChildrenOrderActivity extends AppCompatActivity {
    private Button btnPurple;
    private Button btnGreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_get_children_order);
        btnPurple = findViewById(R.id.btnPurple);
        btnGreen = findViewById(R.id.btnGreen);

        btnPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(btnPurple.getContext(), "点击了紫色按钮", Toast.LENGTH_SHORT).show();
            }
        });
        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(btnPurple.getContext(), "点击了绿色按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }
}