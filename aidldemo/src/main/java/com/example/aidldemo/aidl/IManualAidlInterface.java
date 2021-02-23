package com.example.aidldemo.aidl;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

/**
 * 手动写的代码
 * IBinder《=》IInterface
 * <p>
 * Stub extends Binder implements UserInterface{
 * <p>
 * <p>
 * // 将IBinder转换为具体的UserInterface，可能是本地Binder对象，也可能是远程代理
 * asInterface
 * <p>
 * onTransact：接收远程调用，根据code不同区分具体的方法，然后反序列化参数后调用具体的方法并返回结果给远程
 * <p>
 * asBinder：转换为IBinder实例
 * <p>
 * // UserInterface的实际执行
 * }
 * <p>
 * Proxy implements UserInterface{
 * IBinder
 * <p>
 * // 通过IBinder跨进程调用
 * }
 */
public interface IManualAidlInterface extends IInterface {
    String DESCRIPTOR = "com.example.aidldemo.aidl.IManualAidlInterface";
    // 操作的序号
    int TRANSACTION_addBook = IBinder.FIRST_CALL_TRANSACTION + 0;

    void addBook(long id, String name) throws RemoteException;
}
