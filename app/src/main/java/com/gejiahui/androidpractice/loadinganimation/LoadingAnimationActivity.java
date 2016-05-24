package com.gejiahui.androidpractice.loadinganimation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.gejiahui.androidpractice.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by gejiahui on 2016/5/20.
 */
public class LoadingAnimationActivity extends AppCompatActivity {
    @Bind(R.id.start)
    Button start;
    @Bind(R.id.stop)
    Button stop;
    @Bind(R.id.loading)
    ShapeLoadingView loading;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_loading);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.start)
    void startAnimator()
    {
        loading.startLoading();
    }


    @OnClick(R.id.stop)
    void stopAnimator()
    {
        loading.stopLoading();
    }
}
