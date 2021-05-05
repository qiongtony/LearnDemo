package com.example.learndemo.exception

import android.content.Context
import android.util.Log
import kotlin.system.exitProcess

/**
 * 自定义的异常捕获类
 * 作用：
 * 采集崩溃信息，将崩溃信息可以保存在本地或上传到服务器
 * 定制崩溃的后续处理
 *
 * 使用方式：
 * 在当前进程进行初始化，替换进程默认捕获异常的handler
 */
class CustomUncaughtExceptionHandler(context: Context) : Thread.UncaughtExceptionHandler {
    val defaultHandler: Thread.UncaughtExceptionHandler

    init {
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(t: Thread?, e: Throwable?) {
        Log.i("WWS", "exception = ${e?.toString()}")
        if (!handleException(e)){
            defaultHandler.uncaughtException(t, e)
        }else{
            android.os.Process.killProcess(android.os.Process.myPid())
            exitProcess(0)
        }
    }

    fun handleException(e : Throwable?) : Boolean{
        return false
    }
}