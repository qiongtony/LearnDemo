package com.example.learndemo.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<V : ViewDataBinding> :AppCompatActivity(){
    public lateinit var binding : V


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.bind<V>(LayoutInflater.from(this).inflate(getLayoutResId(), null))!!
        setContentView(binding?.root)
    }

    public abstract fun getLayoutResId() : Int
}