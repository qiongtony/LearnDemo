package com.example.aopdemo.proxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RequestHandler implements InvocationHandler {
    private Request mRealRequest;

    public RequestHandler(Request realRequest) {
        mRealRequest = realRequest;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.e("DynamicProxyActivity", "通过动态代理执行请求前的操作");
        return method.invoke(mRealRequest, args);
    }
}
