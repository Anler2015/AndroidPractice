package com.gejiahui.androidpractice.dragrecyclerview;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by gejiahui on 2016/5/19.
 */
public abstract class OnRecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    private static final String TAG = "OnRecyclerItemClick";
    private GestureDetectorCompat mGestureDetector;
    private RecyclerView mRecyclerView;


    public OnRecyclerItemClickListener(RecyclerView recyclerView) {
        super();
        mRecyclerView = recyclerView;
        mGestureDetector = new GestureDetectorCompat(recyclerView.getContext(), new ItemTouchHelperGestureListener());
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        Log.i("DragRecyclerView","onRequestDisallowInterceptTouchEvent");
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        Log.i("DragRecyclerView","onTouchEvent");
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
       // Log.i("DragRecyclerView","onInterceptTouchEvent");
        return false;
    }



    private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener{

        public ItemTouchHelperGestureListener() {

        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.i("DragRecyclerView","onLongPress");
            View itemView = mRecyclerView.findChildViewUnder(e.getX(),e.getY());
            if(null != itemView){
                RecyclerView.ViewHolder vh = mRecyclerView.getChildViewHolder(itemView);
                int position = mRecyclerView.getChildLayoutPosition(itemView);
                OnItemLongClick(vh,position);
            }
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.i("DragRecyclerView","onSingleTapUp");
            View itemView = mRecyclerView.findChildViewUnder(e.getX(),e.getY());
            if(null != itemView){
                RecyclerView.ViewHolder vh = mRecyclerView.getChildViewHolder(itemView);
                int position = mRecyclerView.getChildLayoutPosition(itemView);
                OnItemClick(vh,position);
            }

            return false;
        }

    }

    public abstract void OnItemClick(RecyclerView.ViewHolder vh, int position);
    public abstract void OnItemLongClick(RecyclerView.ViewHolder vh, int position);



}
