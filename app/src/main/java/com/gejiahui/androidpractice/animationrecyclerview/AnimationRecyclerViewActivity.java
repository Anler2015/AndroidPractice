package com.gejiahui.androidpractice.animationrecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.gejiahui.androidpractice.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gejiahui on 2016/7/12.
 */
public class AnimationRecyclerViewActivity extends AppCompatActivity {
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    List<ItemBean> mDatas = new ArrayList<>();
    AnimationAdapter adapter;
    boolean isOpen = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);
        init();
        adapter = new AnimationAdapter(this,mDatas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    private void init(){
        for(int i =0; i < 20; i++){
            mDatas.add(new ItemBean("1"));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_animation_recyclerview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.edit) {
            if(isOpen){
                isOpen = false;
                item.setTitle("编辑");
                adapter.closeItemAnimation();
            }else{
                isOpen = true;
                item.setTitle("取消");
                adapter.openItemAnimation();
            }

            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
