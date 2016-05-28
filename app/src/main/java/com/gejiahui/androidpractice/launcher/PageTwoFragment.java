package com.gejiahui.androidpractice.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.gejiahui.androidpractice.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gejiahui on 2016/5/27.
 */
public class PageTwoFragment extends LauncherBaseFragment {
    @Bind(R.id.iv_gold)
    ImageView imgGold;
    @Bind(R.id.iv_reward)
    ImageView imgReward;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_two,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void startAnimation() {
        imgReward.setVisibility(View.GONE);
        goldAnimation();
    }

    @Override
    public void stopAnimation() {
        imgGold.clearAnimation();
        imgReward.clearAnimation();
        imgReward.setVisibility(View.GONE);
    }


    private void goldAnimation(){

        TranslateAnimation animation1 = new TranslateAnimation(0,0,0,1200);
        animation1.setDuration(500);
        ScaleAnimation animation2 = new ScaleAnimation(1.0f,0.5f,1.0f,0.5f);
        animation2.setDuration(500);
        AlphaAnimation animation3 = new AlphaAnimation(1.0f,0f);
        animation3.setDuration(1000);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(500);
        animationSet.addAnimation(animation1);
        animationSet.addAnimation(animation2);
        animationSet.addAnimation(animation3);
        animationSet.setFillAfter(true);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
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
        imgGold.startAnimation(animationSet);
    }



    private void rewardAnimation(){
        imgReward.setVisibility(View.VISIBLE);
        ScaleAnimation animation2 = new ScaleAnimation(0f,1.0f,0f,1.0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,1f);
        animation2.setDuration(800);
        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                AlphaAnimation alphaAnimation=new AlphaAnimation(1,0);
                alphaAnimation.setDuration(1000);
                imgReward.startAnimation(alphaAnimation);
                alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {}
                    @Override
                    public void onAnimationRepeat(Animation animation) {}
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //透明度动画结束隐藏图片
                        imgReward.setVisibility(View.GONE);
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imgReward.startAnimation(animation2);


    }


}
