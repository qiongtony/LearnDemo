package com.example.learndemo.hook

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.learndemo.base.BaseActivity
import com.example.learndemo.R
import com.example.learndemo.databinding.ActivityHookBinding
import com.example.learndemo.hook.HookTouchListener.*

class HookActivity : BaseActivity<ActivityHookBinding>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btn1.post {
        val hookHelper = HookHelper()
            hookHelper.hook(this@HookActivity, object : TouchCallback{
                override fun loadMsg(msg : String){
                    Log.e("WWS", "msg = $msg")
                    Toast.makeText(this@HookActivity, msg, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_hook
    }
}