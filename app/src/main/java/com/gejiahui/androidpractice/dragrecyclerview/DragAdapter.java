package com.gejiahui.androidpractice.dragrecyclerview;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gejiahui.androidpractice.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gejiahui on 2016/5/18.
 */
class DragAdapter extends RecyclerView.Adapter<DragAdapter.DragViewHolder>{

    List<DragItem> list;

    public DragAdapter(List<DragItem> list) {
        this.list = list;
    }

    private View.OnLongClickListener mLongClick;

    public void setOnLongClickListener(View.OnLongClickListener listener){
        mLongClick = listener;
    }

    @Override
    public DragViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view,parent,false);
        return new DragViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DragViewHolder holder, int position) {
        holder.img.setImageResource(list.get(position).getImgRes());
        holder.itemName.setText(list.get(position).getItemName());
//        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//
//                return false;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class DragViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.img)
        ImageView img;
        @Bind(R.id.item_name)
        TextView itemName;
        @Bind(R.id.card)
        CardView card;

        public DragViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}