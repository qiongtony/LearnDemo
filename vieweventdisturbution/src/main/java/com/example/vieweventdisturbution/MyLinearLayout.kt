package com.example.vieweventdisturbution

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout

class  MyLinearLayout @JvmOverloads constructor(context: Context?, attributeSet: AttributeSet? = null, defs : Int = 0) : LinearLayout(context, attributeSet, defs){

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.i(javaClass.simpleName, "onInterceptTouchEvent event = ${ev?.actionMasked}")
        return ev?.actionMasked ==  MotionEvent.ACTION_DOWN/*MotionEvent.ACTION_MOVE*/
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i(javaClass.simpleName, "onTouchEvent event = ${event?.actionMasked}")
        return true
    }
}