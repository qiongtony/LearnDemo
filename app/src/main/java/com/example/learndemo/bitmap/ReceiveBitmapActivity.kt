package com.example.learndemo.bitmap

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import com.example.learndemo.BaseActivity
import com.example.learndemo.IRemoteGetBitmap
import com.example.learndemo.R
import com.example.learndemo.databinding.ActivityReceiveBitmapBinding

class ReceiveBitmapActivity : BaseActivity<ActivityReceiveBitmapBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_receive_bitmap
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent != null && intent.extras != null) {
            val binder = IRemoteGetBitmap.Stub.asInterface(intent.extras.getBinder("binder"))

            Toast.makeText(this, "通过putBinder获取", Toast.LENGTH_SHORT).show()
            binding.ivShow.setImageBitmap(binder.bitmap)
        }else if (intent != null){
            Toast.makeText(this, "通过intent直接获取", Toast.LENGTH_SHORT).show()
            binding.ivShow.setImageBitmap(intent.getParcelableExtra<Bitmap>("bitmap"))
        }
    }
}