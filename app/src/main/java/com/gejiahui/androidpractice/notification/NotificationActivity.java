package com.gejiahui.androidpractice.notification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.gejiahui.androidpractice.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by gejiahui on 2016/6/3.
 */
public class NotificationActivity extends AppCompatActivity {

    @Bind(R.id.notification_normal)
    Button normal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);

    }



    @OnClick(R.id.notification_normal)
    void normalClick(){

    }

}
