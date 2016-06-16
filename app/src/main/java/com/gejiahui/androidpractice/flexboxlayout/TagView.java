package com.gejiahui.androidpractice.flexboxlayout;

import android.content.Context;
import android.view.View;
import android.widget.Checkable;
import android.widget.FrameLayout;

import com.gejiahui.androidpractice.utils.DimenUtils;

/**
 * Created by gejiahui on 2016/5/25.
 * <p>
 * 该view主要功能是 为添加的布局增加一个check 状态
 */
public class TagView extends FrameLayout implements Checkable {

    private boolean mChecked ;
    private static final int[] CHECKED_STATE_SET = {
            android.R.attr.state_checked
    };

    public TagView(Context context) {
        super(context);
    }

    public static TagView wrap(Context context, View view) {
        if (null == view) {
            throw new IllegalArgumentException("view can't be null");
        }
        view.setDuplicateParentStateEnabled(true);  //允许复制父控件的状态
        TagView tagView = new TagView(context);
        if (null != view.getLayoutParams()) {

            tagView.setLayoutParams(view.getLayoutParams());

        } else {

            MarginLayoutParams lp = new MarginLayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            int defaultMargin = DimenUtils.dip2px(context, 4);
            lp.setMargins(defaultMargin,
                    defaultMargin,
                    defaultMargin,
                    defaultMargin);
            tagView.setLayoutParams(lp);

        }
        tagView.addView(view);

        return  tagView;
    }

    public static View unwarp(View view)
    {
        if (view == null)
            throw new IllegalArgumentException("view can not be null .");
        if (view instanceof TagView)
        {
            return ((TagView) view).getChildAt(0);
        } else
        {
            return view;
        }
    }

    //添加了check状态
    @Override
    public int[] onCreateDrawableState(int extraSpace)
    {
        int[] states = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked())
        {
            mergeDrawableStates(states, CHECKED_STATE_SET);
        }
        return states;
    }


    @Override
    public void setChecked(boolean checked) {
        if (this.mChecked != checked) {
            this.mChecked = checked;
            refreshDrawableState();
        }
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }
}
