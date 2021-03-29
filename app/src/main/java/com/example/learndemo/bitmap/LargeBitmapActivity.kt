package com.example.learndemo.bitmap

import android.os.Bundle
import android.util.Log
import com.example.learndemo.BaseActivity
import com.example.learndemo.R
import com.example.learndemo.databinding.ActivityLargeBitmapBinding
import java.io.InputStream
class LargeBitmapActivity : BaseActivity<ActivityLargeBitmapBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 这种方式才38M
        var inputStream: InputStream? = null
        try {
            inputStream = assets.open("world_map.jpg"/*"good_girl.jpg"*/)
            binding.ivLarge.inputStream = inputStream
        } catch (e: Exception) {
            Log.e("WWS", e.toString())
        } finally {
            inputStream?.close()
        }
        // 这种120多M，因为图片大概就是96M说明是全加载了
//        binding.ivOriginal.setImageBitmap(BitmapFactory.decodeStream(assets.open("world_map.jpg")))
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_large_bitmap
    }
}