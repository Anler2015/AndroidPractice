package com.gejiahui.androidpractice.loadinganimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.gejiahui.androidpractice.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gejiahui on 2016/5/20.
 */
public class ShapeLoadingView extends FrameLayout {

    public float factor = 1.2f;
    private AnimatorSet  mAnimatorSet = null;
    @Bind(R.id.shape)
    ImageView shape;
    @Bind(R.id.shadow)
    View shadow;
    Context mContext ;
    int[] imgRes = {R.drawable.square, R.drawable.triangle,R.drawable.circle};
    int position = 0;
    public ShapeLoadingView(Context context) {
        this(context,null);
    }


    public ShapeLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }


    public ShapeLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    protected void onFinishInflate() {

        super.onFinishInflate();

        View view = LayoutInflater.from(mContext).inflate(R.layout.animation_shape_loading,null,false);
        ButterKnife.bind(this,view);
        shape.setImageResource(imgRes[position]);
        addView(view);
        setVisibility(View.GONE);
      //  fallAnimation();
    }


    private void upAnimation(){

        //阴影变小动画
        ObjectAnimator shadowScaleXAnimator = ObjectAnimator.ofFloat(shadow, "scaleX",
                2f, 1f);
        ObjectAnimator  shadowScaleYAnimator = ObjectAnimator.ofFloat(shadow, "scaleY",
                0.8f);
        ObjectAnimator  shadowAlphaAnimator = ObjectAnimator.ofFloat(shadow, "alpha",
                1.5f, 0.5f);

        //shape 上抛动画
        ObjectAnimator shapeRotationAnimator = ObjectAnimator.ofFloat(shape, "rotation",
                -180, 0);
        ObjectAnimator shapeTranslationAnimator = ObjectAnimator.ofFloat(shape, "translationY",
                200, 0);
        shapeTranslationAnimator.setInterpolator( new DecelerateInterpolator(factor));


        AnimatorSet  upSet = new AnimatorSet();
        upSet.playTogether(shadowScaleXAnimator, shadowScaleYAnimator,
                shadowAlphaAnimator,shapeRotationAnimator,shapeTranslationAnimator);
        upSet.setDuration(500);
        upSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                    fallAnimation();
            }

        });
        upSet.start();
        mAnimatorSet = upSet;

    }


    private void fallAnimation(){

        //阴影变大动画
        ObjectAnimator shadowScaleXAnimator = ObjectAnimator.ofFloat(shadow, "scaleX",
                1f, 2f);

        ObjectAnimator  shadowScaleYAnimator = ObjectAnimator.ofFloat(shadow, "scaleY",
                1.2f);
        ObjectAnimator  shadowAlphaAnimator = ObjectAnimator.ofFloat(shadow, "alpha",
                0.5f, 1.5f);

        //shape 下坠动画
        ObjectAnimator shapeRotationAnimator = ObjectAnimator.ofFloat(shape, "rotation",
                0, 180);
        ObjectAnimator shapeTranslationAnimator = ObjectAnimator.ofFloat(shape, "translationY",
                0, 200);
        shapeTranslationAnimator.setInterpolator( new AccelerateInterpolator(factor));

        AnimatorSet  fallSet = new AnimatorSet();
        fallSet.playTogether(shadowScaleXAnimator, shadowScaleYAnimator,
                shadowAlphaAnimator,shapeRotationAnimator,shapeTranslationAnimator);
        fallSet.setDuration(500);
        fallSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                position++;
                if(position == imgRes.length){
                    position = 0;
                }
                shape.setImageResource(imgRes[position]);
                upAnimation();
            }

        });
        fallSet.start();
        mAnimatorSet = fallSet;
    }


    private Runnable mFreeFallRunnable = new Runnable() {
        @Override
        public void run() {
            fallAnimation();
        }
    };

    public void startLoading(long delay) {
        if (getVisibility() == View.VISIBLE && mAnimatorSet != null && mAnimatorSet.isRunning()) {
            return;
        }
        this.removeCallbacks(mFreeFallRunnable);
        if (delay > 0) {
            this.postDelayed(mFreeFallRunnable, delay);
        } else {
            this.post(mFreeFallRunnable);
        }
        setVisibility(View.VISIBLE);

    }

    public void startLoading() {
        startLoading(0);

    }

    public void stopLoading() {
        if (mAnimatorSet != null && getVisibility() == View.VISIBLE) {
            if (mAnimatorSet.isRunning()) {
                mAnimatorSet.cancel();
                mAnimatorSet.pause();
                Log.d("animation","pause?" + mAnimatorSet.isRunning());
            }
            mAnimatorSet = null;
        }
        this.removeCallbacks(mFreeFallRunnable);
        setVisibility(View.GONE);
    }





}
