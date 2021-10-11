package com.example.everydayexamdemo.designmode.charcter_11_command

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.everydayexamdemo.databinding.ActivityCustomDrawBinding

/**
 * 使用命令模式实现画板
 * drawInvoker：请求者，发起命令请求
 * drawPath：接收者，操作的实际逻辑在这里执行
 * brush：笔触
 * idraw：抽象的命令，当前包含：绘制和撤销两个命令
 * drawView：
 *      存储绘制命令集合，和撤销命令集合，支持绘制、撤销、重做三种命令
 *
 * 这样的好处：当我们要修改笔触、增加idraw的命令时drawView可以不修改，
 *
 *
 */
class CustomDrawActivity : AppCompatActivity() {
    var path: DrawPath? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCustomDrawBinding.inflate(layoutInflater);
        setContentView(binding.root)
        val paint = Paint()
                .apply {
                    this.strokeWidth = 3f
                    color = Color.RED
                }
        val brush = NormalBrush()
        val drawView = binding.drawView

        drawView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                event ?: return false
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        path = DrawPath(Path(), paint)
                        brush.down(path!!.path, event.x, event.y)
                    }

                    MotionEvent.ACTION_MOVE -> {
                        brush.move(path!!.path, event.x, event.y)
                    }

                    MotionEvent.ACTION_UP -> {
                        Log.e("WWS", "ACTION_UP x = ${event.x} y = ${event.y}")
                        brush.up(path!!.path, event.x, event.y)
                        drawView.add(path!!)
                        drawView.isDrawing = true
                        drawView.isEnabled = true
                        (binding.btnUndo as View).isEnabled = true
                        (binding.btnRedo as View).isEnabled = true
                    }
                    else -> {

                    }
                }
                return true
            }

        })
        (binding.btnUndo as View).setOnClickListener {
            drawView.undo()
            if (!drawView.canUndo()) {
                drawView.isEnabled = false
            }
            binding.btnRedo.isEnabled = true
        }
        (binding.btnRedo as View).setOnClickListener {
            drawView.redo()
            if (!drawView.canRedo()) {
                drawView.isEnabled = false
            }
            binding.btnUndo.isEnabled = true
        }

    }
}