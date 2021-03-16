package com.example.learndemo;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SyncBarrierActivity extends AppCompatActivity {
    private Button
            sendSyncMsg,
            sendASyncMsg,
            sendSyncBarrier,
            removeSyncBarrier;

    protected static final int MSG_WHAT_SYNC = 100;
    protected static final int MSG_WHAT_ASYNC = 101;

    private Handler workHandler;
    private int mToken;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_barrier);

        new Thread() {
            @Override
            public void run() {
                super.run();
                initHandler();
            }

            private void initHandler() {
                Looper.prepare();
                workHandler = new Handler(Looper.myLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        switch (msg.what) {
                            case MSG_WHAT_SYNC: {
                                Log.i("WWS", "收到了同步消息");
                                break;
                            }

                            case MSG_WHAT_ASYNC: {
                                Log.i("WWS", "收到了异步消息");
                                break;
                            }
                            default: {
                                Log.i("WWS", "未知类型的消息");
                                break;
                            }
                        }
                    }
                };
                Looper.loop();
            }
        }.start();

        sendSyncMsg = findViewById(R.id.sendSyncMsg);
        sendASyncMsg = findViewById(R.id.sendASyncMsg);
        sendSyncBarrier = findViewById(R.id.sendSyncBarrier);
        removeSyncBarrier = findViewById(R.id.removeSyncBarrier);

        sendSyncMsg.setOnClickListener(v -> {
            sendSyncMsg();
        });
        sendASyncMsg.setOnClickListener(v -> {
            sendAsyncMsg();
        });

        sendSyncBarrier.setOnClickListener(v -> {
            sendSyncBarrier();

        });

        removeSyncBarrier.setOnClickListener(v -> {
            removeSyncBarrier();
        });
    }

    /**
     * 移除同步屏障
     */
    private void removeSyncBarrier() {
        try {
            MessageQueue queue = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                queue = workHandler.getLooper().getQueue();
                Method method = queue.getClass().getDeclaredMethod("removeSyncBarrier", int.class);
                method.setAccessible(true);
                method.invoke(queue, mToken);
                Log.i("WWS", "移除屏障");
            }

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置同步屏障
     */
    private void sendSyncBarrier() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                MessageQueue queue = workHandler.getLooper().getQueue();
                Method method = queue.getClass().getDeclaredMethod("postSyncBarrier");
                method.setAccessible(true);
                mToken = (int) method.invoke(queue);
            }
            Log.i("WWS", "发送了同步屏障 token = " + mToken);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    // 发送异步消息
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    private void sendAsyncMsg() {
        Message message = Message.obtain();
        message.what = MSG_WHAT_ASYNC;
        message.obj = workHandler;
        message.setAsynchronous(true);
        workHandler.sendMessageDelayed(message, 1000);
    }

    // 发送同步消息
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    private void sendSyncMsg() {
        Message message = Message.obtain();
        message.what = MSG_WHAT_SYNC;
        message.obj = workHandler;
        message.setAsynchronous(false);
        workHandler.sendMessageDelayed(message, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        workHandler.getLooper().quitSafely();
    }
}