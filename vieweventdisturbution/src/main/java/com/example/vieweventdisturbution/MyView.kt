package com.example.vieweventdisturbution

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout

class MyView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defs : Int = 0) : View(context, attributeSet, defs){

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Log.i(javaClass.simpleName + "$this", "dispatchTouchEvent event = ${event?.actionMasked}")
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i(javaClass.simpleName + "$this", "onTouchEvent event = ${event?.actionMasked}")
        return true
    }
}