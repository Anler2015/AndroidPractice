package com.gejiahui.androidpractice.flexboxlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gejiahui.androidpractice.R;
import com.gejiahui.androidpractice.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gejiahui on 2016/5/17.
 */
public class FlexBoxLayoutActivity extends AppCompatActivity {
    @Bind(R.id.tag_layout)
    TagLayout mTagLayout;
    List<String> mDatas;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexboxlayout);
        ButterKnife.bind(this);
        initData();

        TagAdapter<String> adapter = new TagAdapter<String>(mDatas) {
            @Override
            protected View getView(ViewGroup parent, int position, String data) {
                TextView view = (TextView)LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag,parent,false);
                view.setText(data);
                return view;
            }

            @Override
            protected void onSelected(ViewGroup parent, View view, int position) {
                view.setBackgroundResource(R.drawable.checked_bg);
            }

            @Override
            protected void onUnSelected(ViewGroup parent, View view, int position) {
                view.setBackgroundResource(R.drawable.normal_bg);
            }
        };

        mTagLayout.setAdapter(adapter);
        mTagLayout.setTagItemClickListener(new TagLayout.TagItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, Object data, boolean isSelected) {
                if(isSelected){
                    ToastUtils.show((String)data + " ：取消");
                }else{
                    ToastUtils.show((String)data + " ：选中");
                }
            }
        });

    }

    private void initData(){
        mDatas = new ArrayList<>();
        mDatas.add("学霸");
        mDatas.add("90后");
        mDatas.add("dota2");
        mDatas.add("csgo");
        mDatas.add("单身狗");
        mDatas.add("穷比");
        mDatas.add("IT工程师");
        mDatas.add("andorid");
        mDatas.add("程序猿");
        mDatas.add("没人爱");
        mDatas.add("王老五");
        mDatas.add("二次元");
        mDatas.add("智障少年");
        mDatas.add("女神");
        mDatas.add("有志青年");
        mDatas.add("好好学习，天天向上");

    }



}
