package com.gejiahui.androidpractice.animationrecyclerview;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import com.gejiahui.androidpractice.R;
import com.gejiahui.androidpractice.utils.DimenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gejiahui on 2016/7/12.
 */
public class AnimationAdapter extends RecyclerView.Adapter<AnimationAdapter.ItemViewHold> {
    final  static String TAG = "AnimationAdapter";
    List<ItemBean> mDatas = new ArrayList<>();
    List<ItemViewHold> mItemViewList = new ArrayList<>();
    Context mContext;
    boolean isOpen = false;
    public AnimationAdapter(Context context,List<ItemBean> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public ItemViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animation_recycler_view,parent,false);
        ItemViewHold itemViewHold = new ItemViewHold(view);
        mItemViewList.add(itemViewHold);
        return itemViewHold;
    }

    @Override
    public void onBindViewHolder(ItemViewHold holder, int position) {
        Log.i(TAG,"position" + position);
        holder.showCheckBox(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void openItemAnimation() {
        isOpen = true;
        for (ItemViewHold holder : mItemViewList) {
            holder.showAnimator();
        }
    }

    public void closeItemAnimation() {
        isOpen = false;
        for (ItemViewHold holder : mItemViewList) {
            holder.closeAnimator();
        }
    }

    class ItemViewHold extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.checkbox)
        CheckBox mCheckBox;
        @Bind(R.id.item_content_rl)
        RelativeLayout mContent;
        private ItemBean mItemBean;
        int mOffset = 0;
        public ItemViewHold(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mOffset = DimenUtils.dip2px(mContext,32);
            mCheckBox.setAlpha(0);
            mContent.setOnClickListener(this);
        }

        public void closeAnimator(){
            ValueAnimator valueAnimator = new ValueAnimator();
            valueAnimator.setIntValues(0, 1);
            valueAnimator.setDuration(150);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float fraction = valueAnimator.getAnimatedFraction();
                    int endX = (int) (mOffset * (1-fraction));
                    doAnimationSet(endX, (1-fraction));
                }
            });
            valueAnimator.start();
        }

        public void showAnimator(){
            ValueAnimator valueAnimator = new ValueAnimator();
            valueAnimator.setIntValues(0, 1);
            valueAnimator.setDuration(300);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float fraction = valueAnimator.getAnimatedFraction();
                    int endX = (int) (mOffset * fraction);
                    doAnimationSet(endX, fraction);
                }
            });
            valueAnimator.start();
        }

        public void open(){
            mCheckBox.setAlpha(1);
            mContent.setTranslationX(mOffset);
        }

        public void close(){
            mCheckBox.setAlpha(0);
            mContent.setTranslationX(0);
        }


        private void doAnimationSet(int dx, float fraction) {
            mContent.setTranslationX(dx);
            mCheckBox.setScaleX(fraction);
            mCheckBox.setScaleY(fraction);
            mCheckBox.setAlpha(fraction * 255);
        }


        public void showCheckBox(ItemBean itemBean){
            mItemBean = itemBean;
            mCheckBox.setChecked(mItemBean.isChecked());
            if(isOpen){
                open();
            }else{
                close();
            }
        }

        public void setCheckBox() {
            mCheckBox.setChecked(!mCheckBox.isChecked());
            mItemBean.setChecked(mCheckBox.isChecked());
        }

        @Override
        public void onClick(View v) {
            setCheckBox();
        }
    }

}
