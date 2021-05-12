package com.example.learndemo

import android.os.Bundle
import android.widget.TextView
import com.example.learndemo.base.BaseActivity
import com.example.learndemo.databinding.ActivityEventBusBinding
import kotlinx.android.synthetic.main.activity_event_bus.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class EventBusActivity : BaseActivity<ActivityEventBusBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        register(true)

        btnSendEvent.setOnClickListener {
            EventBus.getDefault().post("普通事件")
        }
        btnSendSticky.setOnClickListener {
            EventBus.getDefault().postSticky("粘性事件")
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun receiveMessage(content: String) {
        vgMessage.addView(
                TextView(this).apply {
                    text = content
                }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        register(false)
    }

    fun register(register: Boolean) {
        if (register) {
            EventBus.getDefault().register(this)
        } else {
            EventBus.getDefault().unregister(this)
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_event_bus
    }
}