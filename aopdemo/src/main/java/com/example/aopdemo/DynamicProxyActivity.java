package com.example.aopdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.aopdemo.proxy.Request;
import com.example.aopdemo.proxy.RequestHandler;

import java.lang.reflect.Proxy;

public class DynamicProxyActivity extends AppCompatActivity {
    private static final String TAG = DynamicProxyActivity.class.getSimpleName();
    private Request mRequest = new Request() {
        @Override
        public void request(String url) {
            Log.e(TAG, "请求" + url + "中...");
        }
    };
    private RequestHandler mHandler = new RequestHandler(mRequest);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_proxy);
    }

    public void request(View view) {
        // 创建Proxy实例：Request接口的实例
        Request request = (Request) Proxy.newProxyInstance(getClassLoader(), new Class[]{Request.class}, mHandler);
        // 执行接口的方法
        request.request("http://www.baidu.com");
    }
}