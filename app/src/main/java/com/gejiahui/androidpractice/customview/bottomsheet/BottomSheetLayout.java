package com.gejiahui.androidpractice.customview.bottomsheet;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by gejiahui on 2016/7/7.
 */
public class BottomSheetLayout extends FrameLayout {
    private static final int MARGIN_TOP = 300;
    private int marginTop = MARGIN_TOP;
    private int childCount = 0;
    private ViewGroup mContent;
    private ViewGroup mDrawer;
    private int mHeight;
    private int mDrawerHeight;


    public BottomSheetLayout(Context context) {
        super(context);
    }

    public BottomSheetLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public BottomSheetLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        init();
        super.onFinishInflate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mHeight = getHeight();
        mDrawerHeight = mDrawer.getHeight();
        mDrawer.setTranslationY(mDrawerHeight);

    }



    private void init(){
        childCount = getChildCount();
        if(childCount != 2){
            throw new IllegalArgumentException("the child views must be 2 ! there is "+ childCount +" child views");
        }

        mContent = (ViewGroup) getChildAt(0);
        mDrawer = (ViewGroup) getChildAt(1);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)mDrawer.getLayoutParams();
        params.topMargin = marginTop;
        mDrawer.setLayoutParams(params);

    }

    public void showSheet() {
        mDrawer.animate().translationY(0).setDuration(500).start();
    }


    public void closeSheet(){
        mDrawer.animate().translationY(mDrawerHeight).setDuration(500).start();
    }


}
