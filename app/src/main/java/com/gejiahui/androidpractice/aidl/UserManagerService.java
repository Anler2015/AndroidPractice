package com.gejiahui.androidpractice.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by gejiahui on 2016/5/16.
 */
public class UserManagerService extends Service {

    private static final String  TAG = "UserManagerService";

    private CopyOnWriteArrayList<User> mUserList = new CopyOnWriteArrayList<>();

    private Binder mBinder = new IUserManager.Stub(){

        @Override
        public List<User> getUserList() throws RemoteException {
            return mUserList;
        }

        @Override
        public void addUser(User user) throws RemoteException {
            mUserList.add(user);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mUserList.add(new User("anler",23,true));
        mUserList.add(new User("coco",22,false));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
