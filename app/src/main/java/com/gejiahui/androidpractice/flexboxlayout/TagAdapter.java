package com.gejiahui.androidpractice.flexboxlayout;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gejiahui on 2016/5/24.
 */
public abstract class TagAdapter<T>  {
    private List<T> mDatas;

    public TagAdapter(List<T> datas) {
        if(datas == null){
            this.mDatas = new ArrayList<>(0);
        }
        else{
            this.mDatas = datas;
        }
    }

    protected abstract View getView(ViewGroup parent,int position, T data);

    protected void onSelected(ViewGroup parent, View view, int position){

    }

    protected void onUnSelected(ViewGroup parent, View view, int position){

    }

    protected T getItem(int position){
        return mDatas.get(position);
    }

    protected int getCount(){
        return mDatas.size();
    }

}

