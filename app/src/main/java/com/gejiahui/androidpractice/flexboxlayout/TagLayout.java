package com.gejiahui.androidpractice.flexboxlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.gejiahui.androidpractice.utils.DimenUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by gejiahui on 2016/5/24.
 */
public class TagLayout extends com.google.android.flexbox.FlexboxLayout {

    private TagAdapter mAdapter;
    private TagItemClickListener mTagItemClickListener;

    public TagLayout(Context context, AttributeSet attrs) {
        super(context,attrs);
        setFlexDirection(FLEX_DIRECTION_ROW);
        setFlexWrap(FLEX_WRAP_WRAP);
    }


    public void setAdapter(TagAdapter adapter){
        mAdapter = adapter;
        removeAllViews();
        for(int i = 0,n = mAdapter.getCount(); i < n; i++){
            View view = mAdapter.getView(this,i,mAdapter.getItem(i));

            if(view.getLayoutParams() == null){
                MarginLayoutParams lp = new MarginLayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                int defaultMargin = DimenUtils.dip2px(this.getContext(), 4);
                lp.setMargins(defaultMargin,
                        defaultMargin,
                        defaultMargin,
                        defaultMargin);
            }
            addView(view);
            //添加选中的响应
            viewClickableSet(view,i);
        }

    }

    public void setTagItemClickListener(TagItemClickListener listener){
        mTagItemClickListener = listener;
    }


    private Set<Integer> mSelectedItem = new HashSet<>();


    private void viewClickableSet(final View view, final int position){
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSelected = mSelectedItem.contains(position);
                if(mTagItemClickListener != null){

                    mTagItemClickListener.OnItemClick(v,position,mAdapter.getItem(position),isSelected);
                }

                if(isSelected){
                    mSelectedItem.remove(position);
                    mAdapter.onUnSelected(TagLayout.this,v,position);


                }else{
                    mSelectedItem.add(position);
                    mAdapter.onSelected(TagLayout.this,v,position);
                }
            }
        });
    }

    interface TagItemClickListener<T>{
        void OnItemClick(View view,int position,T data,boolean isSelected);
    }
}
