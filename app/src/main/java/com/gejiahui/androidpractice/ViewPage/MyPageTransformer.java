package com.gejiahui.androidpractice.ViewPage;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by gejiahui on 2016/5/11.
 */
public class MyPageTransformer implements ViewPager.PageTransformer {
    private static final float DEFAULT_MIN_ALPHA = 0.5f;
    private float mMinAlpha = DEFAULT_MIN_ALPHA;

    private static final float DEFAULT_MIN_SCALE = 0.85f;
    private float mMinScale = DEFAULT_MIN_SCALE;

    private static final float DEFAULT_MAX_ROTATE = 15.0f;
    private float mMaxRotate = DEFAULT_MAX_ROTATE;

    @Override
    public void transformPage(View view, float position) {

        if (position < -1) {
            view.setAlpha(mMinAlpha);
            view.setScaleX(mMinScale);
            view.setScaleY(mMinScale);
            view.setPivotX(view.getWidth());
            view.setPivotY(view.getHeight());
            view.setRotation(mMaxRotate * -1);

        } else if (position <= 1) { // [-1,1]
            float factor;
            float scale;
            if (position < 0) //[0，-1]
            {
                factor = mMinAlpha + (1 - mMinAlpha) * (1 + position);
                scale = mMinScale + (1 - mMinScale) * (1 + position);
                view.setPivotX(view.getWidth() * (0.5f + 0.5f * (-position)));
                view.setPivotY(view.getHeight());
                view.setRotation(mMaxRotate * position);
            } else//[1，0]
            {
                factor = mMinAlpha + (1 - mMinAlpha) * (1 - position);
                scale = mMinScale + (1 - mMinScale) * (1 - position);
                view.setPivotX(view.getWidth() * 0.5f * (1 - position));
                view.setPivotY(view.getHeight());
                view.setRotation(mMaxRotate * position);
            }

            view.setAlpha(factor);
            view.setScaleX(scale);
            view.setScaleY(scale);
        } else { // (1,+Infinity]
            view.setAlpha(mMinAlpha);
            view.setScaleX(mMinScale);
            view.setScaleY(mMinScale);
            view.setRotation(mMaxRotate);
            view.setPivotX(0);
            view.setPivotY(view.getHeight());
        }
    }
}
