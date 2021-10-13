package com.example.everydayexamdemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.everydayexamdemo.databinding.ActivityMainBinding;
import com.example.everydayexamdemo.designmode.chapter_13_memento.NoteActivity;
import com.example.everydayexamdemo.designmode.chapter_11_command.CustomDrawActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LiveData data = new MutableLiveData <String>();
        data.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {

            }
        });
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnJumpToFragmentRetainPage.setOnClickListener(v -> {
            Log.e("WWS", "click btnJumpToFragmentRetainPage");
            openPage(FragmentRetainActivity.class);
        });
        binding.btnJumpToExceptionPage.setOnClickListener(v -> openPage(UncaughExceptionActivity.class));
        binding.btnJumpToGlidePage.setOnClickListener(v -> openPage(GlideActivity.class));
        binding.btnJumpToDrawViewPage.setOnClickListener(v -> openPage(CustomDrawActivity.class));
        binding.btnJumpToMemotoPage.setOnClickListener(v -> openPage(NoteActivity.class));
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