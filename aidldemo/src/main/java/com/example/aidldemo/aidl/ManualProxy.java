package com.example.aidldemo.aidl;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import com.example.aidldemo.IManualAidlInterface;

public class ManualProxy implements IManualAidlInterface {

    IBinder mRemote;
    public static IManualAidlInterface sDefaultImpl;

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
            /*if (!status && getDefaultImpl() != null){
                getDefaultImpl().addBook(id, name);
                return;
            }*/
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
