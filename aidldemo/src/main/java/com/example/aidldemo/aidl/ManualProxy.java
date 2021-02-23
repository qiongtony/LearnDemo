package com.example.aidldemo.aidl;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public class ManualProxy implements IManualAidlInterface {

    IBinder mRemote;

    public ManualProxy(IBinder remote) {
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
            boolean status = mRemote.transact(TRANSACTION_addBook, data, reply, 0);
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
