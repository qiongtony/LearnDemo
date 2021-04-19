package com.example.learndemo

import android.Manifest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.learndemo.databinding.ActivityOkHttpBinding
import com.example.learndemo.util.SquareUtils
import kotlinx.android.synthetic.main.activity_ok_http.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class OkHttpActivity : BaseActivity<ActivityOkHttpBinding>() {

    companion object{
        const val PHOTO_URL = "http://ww1.sinaimg.cn/large/cecf3e86gy1gpprkqsuh6j20f60d8dg3.jpg"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                    ), 100)
        }

        binding.btnDownload.setOnClickListener {
            download()
        }

        binding.btnDownloadProgress.setOnClickListener {
            downloadWithProgress()
        }
    }

    fun download(){
        SquareUtils.getClient().newCall(Request.Builder()
                .url(PHOTO_URL)
                .build()).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.i("WWS", "onFailure e = ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.i("WWS", "onResponse response.code = ${response.code}")
            }

        })
    }

    fun downloadWithProgress(){
        SquareUtils.getProgressBarClient {
            binding.tvProgress.text = "当前进度为：$it"
        }.newCall(Request.Builder()
                .url(PHOTO_URL)
                .build()).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.i("WWS", "onFailure e = ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.i("WWS", "onResponse response.code = ${response.code}")
            }

        })
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_ok_http
    }
}