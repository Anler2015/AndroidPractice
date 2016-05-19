package com.gejiahui.androidpractice.dragrecyclerview;

import android.app.Service;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.gejiahui.androidpractice.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gejiahui on 2016/5/18.
 */
public class DragRecyclerViewActivity extends AppCompatActivity {
    private static final String TAG = "DragRecyclerView";
    @Bind(R.id.recycler_view)
    RecyclerView recycler_view;

    List<DragItem> list;
    ItemTouchHelper itemTouchHelper;
    DragAdapter adapter;
    Vibrator vib;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);
        initdata();
        vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        adapter = new DragAdapter(list);
        recycler_view.setAdapter(adapter);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                final int swipeFlags = ItemTouchHelper.LEFT;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                int fromPosition = viewHolder.getAdapterPosition();//得到拖动ViewHolder的position
                int toPosition = target.getAdapterPosition();//得到目标ViewHolder的position
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(list, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(list, i, i - 1);
                    }
                }
                adapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                list.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition()); //adapter.notifyDataSetChanged() 不会有动画效果
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                    ((DragAdapter.DragViewHolder) viewHolder).card.setCardBackgroundColor(Color.LTGRAY);
                    vib.vibrate(70);
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                ((DragAdapter.DragViewHolder) viewHolder).card.setCardBackgroundColor(Color.WHITE);
            }
        });
        itemTouchHelper.attachToRecyclerView(recycler_view);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.addOnItemTouchListener(new OnRecyclerItemClickListener(recycler_view) {
            @Override
            public void OnItemClick(RecyclerView.ViewHolder vh, int position) {
                Log.i(TAG,"position :" + position);
            }

            @Override
            public void OnItemLongClick(RecyclerView.ViewHolder vh, int position) {
                Log.i(TAG,"long position :" + position);
            }
        });
    }


    private void initdata() {
        list = new ArrayList<>();
        list.add(new DragItem("item1", R.drawable.item1));
        list.add(new DragItem("item2", R.drawable.item2));
        list.add(new DragItem("item3", R.drawable.item3));
        list.add(new DragItem("item4", R.drawable.item4));
        list.add(new DragItem("item5", R.drawable.item5));
        list.add(new DragItem("item6", R.drawable.item6));
        list.add(new DragItem("item7", R.drawable.item7));
        list.add(new DragItem("item8", R.drawable.item8));
    }

}
