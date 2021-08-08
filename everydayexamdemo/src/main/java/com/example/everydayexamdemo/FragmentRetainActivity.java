package com.example.everydayexamdemo;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

/**
 * 旋转屏幕，fragment实例不会重建
 */
public class FragmentRetainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_retain);
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("blank");
        if (fragment == null){
            fragment = BlankFragment.newInstance();
        }
        // 屏幕旋转不会重建
        fragment.setRetainInstance(true);
        Log.e(getClass().getSimpleName(), "WWS fragment = " + fragment);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, fragment, "blank")
                .commitAllowingStateLoss();
    }
}
