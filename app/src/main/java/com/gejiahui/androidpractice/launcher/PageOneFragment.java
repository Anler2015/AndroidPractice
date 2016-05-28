package com.gejiahui.androidpractice.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.gejiahui.androidpractice.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gejiahui on 2016/5/26.
 */
public class PageOneFragment extends LauncherBaseFragment {
    private final  static  String  TAG = "PageOneFragment";
    @Bind(R.id.iv_private_message_like_video)
    ImageView imgVideo;
    @Bind(R.id.iv_private_message_think_reward)
    ImageView imgReward;
    @Bind(R.id.iv_private_message_watch_movie)
    ImageView imgMovie;
    @Bind(R.id.private_message_this_week)
    ImageView imgWeek;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_one, container, false);
        ButterKnife.bind(this, view);
        Log.i(TAG,"onCreateView");

        return view;
    }

    @Override
    public void startAnimation() {
        videoAnimation();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        Log.i(TAG,"onResume");
        startAnimation();
        super.onResume();
    }

    @Override
    public void stopAnimation() {
        imgVideo.clearAnimation();
        imgReward.clearAnimation();
        imgMovie.clearAnimation();
        imgWeek.clearAnimation();
    }

    private void videoAnimation() {
        imgVideo.setVisibility(View.VISIBLE);
        ScaleAnimation animation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f);
        animation.setDuration(500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                rewardAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imgVideo.startAnimation(animation);
    }


    private void rewardAnimation() {
        imgReward.setVisibility(View.VISIBLE);
        ScaleAnimation animation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f);
        animation.setDuration(500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                movieAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imgReward.startAnimation(animation);
    }


    private void movieAnimation() {
        imgMovie.setVisibility(View.VISIBLE);
        ScaleAnimation animation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f);
        animation.setDuration(500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                weekAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imgMovie.startAnimation(animation);
    }

    private void weekAnimation() {
        imgWeek.setVisibility(View.VISIBLE);
        ScaleAnimation animation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f);
        animation.setDuration(500);
        imgWeek.startAnimation(animation);
    }

}
