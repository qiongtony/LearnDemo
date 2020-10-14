package com.example.learndemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent[] intents = new Intent[2];
        intents[0] = new Intent(this, SecondActivity.class);
        intents[1] = new Intent(this, ThridActivity.class);
        startActivities(intents);
    }
}