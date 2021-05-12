package com.example.learndemo

import android.content.Intent
import android.os.Bundle
import com.example.learndemo.base.BaseActivity
import com.example.learndemo.bitmap.BitmapOptActivity
import com.example.learndemo.databinding.ActivityMainBinding
import com.example.learndemo.hook.HookActivity
import android.view.View
import com.example.learndemo.exception.MyUncaughtExceptionActivity
import com.example.learndemo.thirdlibrary.ThirdLibraryActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view : View

        binding.btnBitmap.setOnClickListener {
            startActivity(Intent(this@MainActivity, BitmapOptActivity::class.java))
        }
        binding.btnSyncBarrier.setOnClickListener {
            startActivity(Intent(this@MainActivity, SyncBarrierActivity::class.java))
        }

        binding.btnThirdLibrary.setOnClickListener {
            startActivity(Intent(this@MainActivity, ThirdLibraryActivity::class.java))
        }

        binding.btnHook.setOnClickListener {
            startActivity(Intent(this@MainActivity, HookActivity::class.java))
        }

        binding.btnEventBus.setOnClickListener {
            startActivity(Intent(this@MainActivity, EventBusActivity::class.java))
        }

        binding.btnNative.setOnClickListener {
            startActivity(Intent(this@MainActivity, JniActivity::class.java))
        }

        binding.btnOkHttp.setOnClickListener {
            startActivity(Intent(this@MainActivity, OkHttpActivity::class.java))
        }
        binding.btnUncaughtException.setOnClickListener {
            startActivity(Intent(this@MainActivity, MyUncaughtExceptionActivity::class.java))
        }
    }

    override fun getLayoutResId(): Int = R.layout.activity_main
}