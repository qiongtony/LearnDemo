package com.example.learndemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.example.learndemo.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnBitmap.setOnClickListener {
            startActivity(Intent(this@MainActivity, BitmapOptActivity::class.java))
        }
        binding.btnSyncBarrier.setOnClickListener {
            startActivity(Intent(this@MainActivity, SyncBarrierActivity::class.java))
        }
    }

    override fun getLayoutResId(): Int = R.layout.activity_main
}