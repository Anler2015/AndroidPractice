package com.gejiahui.androidpractice.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.gejiahui.androidpractice.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gejiahui on 2016/5/25.
 */
public class FirstLauncherActivity extends Activity {

    @Bind(R.id.view_page)
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexboxlayout);
        ButterKnife.bind(this);

    }

}
