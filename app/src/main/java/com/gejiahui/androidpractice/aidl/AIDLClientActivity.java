package com.gejiahui.androidpractice.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.gejiahui.androidpractice.R;

import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by gejiahui on 2016/5/17.
 */
public class AIDLClientActivity extends AppCompatActivity {
    @Bind(R.id.show_user_list)
    Button showList;
    @Bind(R.id.add_user)
    Button addUser;
    @Bind(R.id.user_text)
    TextView userText;
    IUserManager userManager;
    private static final String TAG = "AIDLClientActivity";

    static int id = 0;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            userManager = IUserManager.Stub.asInterface(service);
            try{
                List<User> userList = userManager.getUserList();
                for(User u : userList ){
                    Log.i(TAG,"user name : " + u.getName() + " | user age : " + u.getAge() + " | is male : " + u.isMale());
                }
                userManager.addUser(new User("xiaogua",24,true));

                List<User> newList = userManager.getUserList();
                Log.i(TAG,"-------------------add new user---------------------------------------");
                for(User u : newList ){
                    Log.i(TAG,"user name : " + u.getName() + " | user age : " + u.getAge() + " | is male : " + u.isMale());
                }

            }catch (RemoteException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        ButterKnife.bind(this);
        Intent intent = new Intent(this,UserManagerService.class);
        bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
    }


    @OnClick(R.id.show_user_list)
    void showUser(){
        try{
            List<User> userList = userManager.getUserList();
            String nameList = "";
            for(User u : userList ){
                Log.i(TAG,"user name : " + u.getName() + " | user age : " + u.getAge() + " | is male : " + u.isMale());
                nameList += "name : " + u.getName() + " | age : " + u.getAge() + " | is male : " + u.isMale()+ "\r\n";
            }
            userText.setText(nameList);
            Log.i(TAG,""+Thread.currentThread().getName());
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    @OnClick(R.id.add_user)
    void addUser(){
        try {
            User newUser = new User("user"+id ,new Random().nextInt(30),true);
            userManager.addUser(newUser);

            id ++;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();
    }
}
