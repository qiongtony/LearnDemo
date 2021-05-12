package com.example.learndemo.thirdlibrary

import android.app.Activity
import android.os.Bundle
import com.example.learndemo.base.BaseActivity
import com.example.learndemo.R
import com.example.learndemo.databinding.ActivityThirdLibraryBinding

class ThirdLibraryActivity : BaseActivity<ActivityThirdLibraryBinding>() {
    companion object{

        private var activity : Activity? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnLeakCanary.setOnClickListener {
            activity = this
            finish()
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_third_library
    }
}