package com.gejiahui.androidpractice.launcher;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gejiahui.androidpractice.R;
import com.gejiahui.androidpractice.utils.DimenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gejiahui on 2016/5/25.
 */
public class FirstLauncherActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private static final String TAG = "FirstLauncherActivity";
    @Bind(R.id.view_page)
    ViewPager viewPager;
    @Bind(R.id.dots)
    LinearLayout dots;
    private List<LauncherBaseFragment> mPageList = new ArrayList<>();
    private FirstLaunchAdapter mAdapter;
    private  int current = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_launcher);
        ButterKnife.bind(this);
        initData();
        initIndicator(3);
        mAdapter = new FirstLaunchAdapter(getSupportFragmentManager(),mPageList);
        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(this);

    }


    private void initData(){

        mPageList.add(new WelcomeFragment());
        mPageList.add(new WelcomeFragment());
        mPageList.add(new WelcomeFragment());


    }


    private void initIndicator(int num){
        if(num <= 0){
            throw new IllegalArgumentException("num must greater than 0");
        }
        for(int i = 0; i < num; i++){
            ImageView view = new ImageView(this);
            if(current == i){
                view.setImageResource(R.drawable.page_indicator_focused);
            }else{
                view.setImageResource(R.drawable.page_indicator_unfocused);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin = DimenUtils.dip2px(this,12);
            layoutParams.rightMargin = DimenUtils.dip2px(this,12);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = dots.indexOfChild(v);
                    viewPager.setCurrentItem(position);
                    current = position;
                    Log.v(TAG,"current = "+current);
                }
            });
            dots.addView(view,layoutParams);

        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for(int i = 0; i < dots.getChildCount(); i++){
            if(position == i){
                  ((ImageView)dots.getChildAt(i)).setImageResource(R.drawable.page_indicator_focused);
            }else{
                ((ImageView)dots.getChildAt(i)).setImageResource(R.drawable.page_indicator_unfocused);
            }
        }
        current = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
