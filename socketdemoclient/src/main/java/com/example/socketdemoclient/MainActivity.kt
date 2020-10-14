package com.example.socketdemoclient

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.Socket
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    var mMainHandler: Handler? = null;

    var socket: Socket? = null

    var mThreadPool: ExecutorService? = null

    // 输入流对象
    var inputStream: InputStream? = null

    // 输入流读取器对象
    var isReader: InputStreamReader? = null
    var bufferReader: BufferedReader? = null

    // 接收服务器发送过来的消息
    var response: String? = null

    var outputStream: OutputStream? = null

    var btnConnect: Button? = null
    var btnDisconnect: Button? = null
    var btnSend: Button? = null

    var tvReceive: TextView? = null
    var tvReceiveMsg: TextView? = null

    var mEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnConnect = findViewById(R.id.connect)
        btnDisconnect = findViewById(R.id.disconnect)
        btnSend = findViewById(R.id.send)

        tvReceive = findViewById(R.id.Receive)
        tvReceiveMsg = findViewById(R.id.receive_message)

        mEditText = findViewById(R.id.edit)

        mThreadPool = Executors.newCachedThreadPool()

        mMainHandler = object : Handler() {
            override fun handleMessage(msg: Message) {
                when (msg?.what) {
                    0 -> {
                        receive_message.text = response
                    }
                }
                super.handleMessage(msg)
            }
        }
        btnConnect?.setOnClickListener {
                mThreadPool?.execute(Runnable {
                         // 匿名函数
                        // 创建Socket
                        socket = Socket("192.168.1.172", 8989)
                        println(socket?.isConnected)
                })
        }
        tvReceive?.setOnClickListener {
            // 在子线程执行耗时操作
            mThreadPool?.execute{
                // 读Socket发的数据
                inputStream = socket?.getInputStream()

                isReader = InputStreamReader(inputStream)
                bufferReader = BufferedReader(isReader)

                response = bufferReader?.readLine()

                val msg = Message.obtain()
                msg.what = 0
                mMainHandler?.sendMessage(msg)
            }
        }

        btnSend?.setOnClickListener {
            mThreadPool?.execute {
                // C发送数据到S
                outputStream = socket?.getOutputStream()

                outputStream?.write((mEditText?.text.toString() + "\n").toByteArray());

                // 步骤3：发送数据到服务端
                outputStream?.flush()
            }
        }
        // 断开客户端 & 服务器的链接
        btnDisconnect?.setOnClickListener {
            // 断开 客户端发送到服务器的连接，即关闭输出流对象OutputStream
            outputStream?.close()

            // 断开 服务器发送到客户端的连接，即关闭输入流读取器对象BufferedReader
            bufferReader?.close()

            // 关闭整个Socket连接
            socket?.close()

            println(socket?.isConnected)
        }
    }
}
