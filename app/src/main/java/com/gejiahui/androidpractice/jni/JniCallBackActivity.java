package com.gejiahui.androidpractice.jni;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.gejiahui.androidpractice.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by gejiahui on 2016/6/28.
 */
public class JniCallBackActivity extends AppCompatActivity {
    @Bind(R.id.show_user_list)
    Button jniCallBack;
    @Bind(R.id.add_user)
    Button jniStaticCallBack;

    JniLibrary jniLibrary;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        ButterKnife.bind(this);
        jniLibrary = new JniLibrary();
        jniCallBack.setText("jni回调java函数");
        jniStaticCallBack.setText("jni回调java静态函数");
    }


    @OnClick(R.id.show_user_list)
    void jniCallBack(){
        jniLibrary.getTimByJni();
    }

    @OnClick(R.id.add_user)
    void jniStaticCallBack(){
        jniLibrary.getTimeStaticByJni();
    }



}
