package com.example.learndemo.exception

import android.os.Bundle
import com.example.learndemo.R
import com.example.learndemo.base.BaseActivity
import com.example.learndemo.databinding.ActivityMyUncaughtExceptionBinding

class MyUncaughtExceptionActivity : BaseActivity<ActivityMyUncaughtExceptionBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnThrowException.setOnClickListener {
            val result = 1 / 0
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_my_uncaught_exception
    }
}