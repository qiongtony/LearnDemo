package com.example.aidldemo.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class BaseManualBinder extends Binder implements IManualAidlInterface {

    public static IManualAidlInterface asInterface(IBinder obj){
        if (obj == null){
            return null;
        }
        IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        // 进程相同，返回本地对象
        if (iin != null && iin instanceof IManualAidlInterface){
            return (IManualAidlInterface) iin;
        }
        // 返回代理对象
        return new ManualProxy(obj);
    }

    public BaseManualBinder(){
        this.attachInterface(this, DESCRIPTOR);
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
                // 反序列数据,序列化和反序列化的顺序要保存一致
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

    @Override
    public IBinder asBinder() {
        return this;
    }
}
