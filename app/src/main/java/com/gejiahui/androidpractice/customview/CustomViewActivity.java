package com.gejiahui.androidpractice.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.gejiahui.androidpractice.R;
import com.gejiahui.androidpractice.customview.backgroundviewpage.BgFragment;

/**
 * Created by gejiahui on 2016/6/29.
 */
public class CustomViewActivity extends AppCompatActivity {


    FragmentManager fm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customview);

        fm = getSupportFragmentManager();
        BgFragment bgFragment = new BgFragment();
        changeCustomView(bgFragment);
    }


    private void changeCustomView(Fragment fragment){
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.content,fragment);
        fragmentTransaction.commit();

    }


}
