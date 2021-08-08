package com.example.learndemo.hook

import android.view.MotionEvent
import android.view.View
import java.util.*

class HookTouchListener(val touchCallback: TouchCallback?, val onTouchListener: View.OnTouchListener) : View.OnTouchListener {
    companion object {
        private val linkedList = LinkedList<View>()
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        event?.apply {
            when (actionMasked) {
                MotionEvent.ACTION_DOWN
                -> {
                    linkedList.add(v!!)
                }
                MotionEvent.ACTION_UP -> {
                    // 拿最顶上的View
                    if (linkedList.isNotEmpty() && linkedList.first != null) {
                        touchCallback?.loadMsg(
                                "${linkedList.first?.context!!.resources.getResourceEntryName(linkedList.first.id)}:${linkedList.first?.javaClass?.name}"
                        )
                        linkedList.clear()
                    }
                }
            }
        }
        return onTouchListener.onTouch(v, event)
    }

    interface TouchCallback {
        fun loadMsg(msg: String)
    }
}