package com.example.aidldemo;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 手动写的代码
 * IBinder《=》IInterface
 *
 * Stub extends Binder implements UserInterface{
 *
 *
 *     // 将IBinder转换为具体的UserInterface，可能是本地Binder对象，也可能是远程代理
 *     asInterface
 *
 *     onTransact：接收远程调用，根据code不同区分具体的方法，然后反序列化参数后调用具体的方法并返回结果给远程
 *
 *     asBinder：转换为IBinder实例
 *
 *     // UserInterface的实际执行
 * }
 *
 * Proxy implements UserInterface{
 *     IBinder
 *
 *     // 通过IBinder跨进程调用
 * }
 *
 */
public interface IManualAidlInterface extends IInterface {

    void addBook(long id, String name) throws RemoteException;

    public static abstract class Stub extends Binder implements IManualAidlInterface{
        public static final String DESCRIPTOR = "com.example.aidldemo.IManualAidlInterface";
        // 操作的序号
        static final int TRANSACTION_addBook = IBinder.FIRST_CALL_TRANSACTION + 0;
        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        public static  IManualAidlInterface asInterface(IBinder obj){
            if (obj == null){
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            // 进程相同，返回本地对象
            if (iin != null && iin instanceof IManualAidlInterface){
                return (IManualAidlInterface) iin;
            }
            // 返回代理对象
            return new Proxy(obj);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
            String descriptor = DESCRIPTOR;
            switch (code){
                case INTERFACE_TRANSACTION:{
                    reply.writeString(descriptor);
                    return true;
                }
                case TRANSACTION_addBook:{
                    // 反序列数据
                    this.addBook(data.readLong(), data.readString());
                    data.enforceInterface(descriptor);
                    reply.writeNoException();
                    return true;
                }
                default:
                {
                    return super.onTransact(code, data, reply, flags);
                }
            }
        }

        // 默认实现不是必须的
        public static boolean setDefaultImpl(IManualAidlInterface impl){
            if (Proxy.sDefaultImpl != null){
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null){
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IManualAidlInterface getDefaultImpl(){
            return Proxy.sDefaultImpl;
        }

        static class Proxy implements IManualAidlInterface{

            IBinder mRemote;
            public static IManualAidlInterface sDefaultImpl;

            public Proxy(IBinder remote) {
                mRemote = remote;
            }

            @Override
            public IBinder asBinder() {
                return mRemote;
            }

            @Override
            public void addBook(long id, String name) throws RemoteException {
                Parcel data = Parcel.obtain();
                Parcel reply = Parcel.obtain();
                // 将参数进行序列化
                data.writeLong(id);
                data.writeString(name);
                try {
                data.writeInterfaceToken(DESCRIPTOR);
                boolean status = mRemote.transact(IManualAidlInterface.Stub.TRANSACTION_addBook, data, reply, 0);
                if (!status && getDefaultImpl() != null){
                    getDefaultImpl().addBook(id, name);
                    return;
                }
                reply.readException();
                }finally {
                    reply.recycle();
                    data.recycle();
                }
            }

            public String getInterfaceDescriptor(){
                return DESCRIPTOR;
            }
        }
    }
}
