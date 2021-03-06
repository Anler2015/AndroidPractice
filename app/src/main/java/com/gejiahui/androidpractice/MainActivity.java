package com.gejiahui.androidpractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gejiahui.androidpractice.aidl.AIDLClientActivity;
import com.gejiahui.androidpractice.animationrecyclerview.AnimationRecyclerViewActivity;
import com.gejiahui.androidpractice.blur.BlurImgActivity;
import com.gejiahui.androidpractice.customview.CustomViewActivity;
import com.gejiahui.androidpractice.dragrecyclerview.DragRecyclerViewActivity;
import com.gejiahui.androidpractice.flexboxlayout.FlexBoxLayoutActivity;
import com.gejiahui.androidpractice.gps.GpsActivity;
import com.gejiahui.androidpractice.jni.JniCallBackActivity;
import com.gejiahui.androidpractice.launcher.FirstLauncherActivity;
import com.gejiahui.androidpractice.loadinganimation.LoadingAnimationActivity;
import com.gejiahui.androidpractice.notification.NotificationActivity;
import com.gejiahui.androidpractice.retrofitdemo.RetrofitActivity;
import com.gejiahui.androidpractice.viewpage.ViewPageActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.list)
    ListView mPracticeList;

    private MyAdapter mAdapter;
    private List<Practice> mPractices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();

        mAdapter = new MyAdapter();
        mPracticeList.setAdapter(mAdapter);
        mPracticeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Practice practice = mPractices.get(position);
                startActivity(practice.intent);
            }
        });
        ViewConfiguration viewConfiguration = ViewConfiguration.get(this);
        int touchSlop = viewConfiguration.getScaledTouchSlop();
        Log.i("gjh","touchSlop"+touchSlop);

        int minimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        Log.i("gjh","minimumVelocity"+minimumVelocity);
        int maximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        Log.i("gjh","maximumVelocity"+maximumVelocity);

        boolean isHavePermanentMenuKey = viewConfiguration.hasPermanentMenuKey();
        Log.i("gjh","isHavePermanentMenuKey"+isHavePermanentMenuKey);

        int doubleTapTimeout = ViewConfiguration.getDoubleTapTimeout();
        Log.i("gjh","doubleTapTimeout"+doubleTapTimeout);

        int longPressTimeout = ViewConfiguration.getLongPressTimeout();
        Log.i("gjh","longPressTimeout"+longPressTimeout);

        int keyRepeatTimeout = ViewConfiguration.getKeyRepeatTimeout();
        Log.i("gjh","keyRepeatTimeout"+keyRepeatTimeout);

    }

    private void initData() {
        mPractices = new ArrayList<>();
        mPractices.add(new Practice("recycler animation", new Intent(this, AnimationRecyclerViewActivity.class)));
        mPractices.add(new Practice("Retrofit", new Intent(this, RetrofitActivity.class)));
        mPractices.add(new Practice("custom view", new Intent(this, CustomViewActivity.class)));
        mPractices.add(new Practice("PageTransformer", new Intent(this, ViewPageActivity.class)));
        mPractices.add(new Practice("AIDL", new Intent(this, AIDLClientActivity.class)));
        mPractices.add(new Practice("TagLayout", new Intent(this, FlexBoxLayoutActivity.class)));
        mPractices.add(new Practice("DragRecyclerView", new Intent(this, DragRecyclerViewActivity.class)));
        mPractices.add(new Practice("58 loading animation", new Intent(this, LoadingAnimationActivity.class)));
        mPractices.add(new Practice("first launcher view page", new Intent(this, FirstLauncherActivity.class)));
        mPractices.add(new Practice("notification", new Intent(this, NotificationActivity.class)));
        mPractices.add(new Practice("Blur img", new Intent(this, BlurImgActivity.class)));
        mPractices.add(new Practice("GPS", new Intent(this, GpsActivity.class)));
        mPractices.add(new Practice("Jni Call Back ", new Intent(this, JniCallBackActivity.class)));

    }


    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mPractices.size();
        }

        @Override
        public Object getItem(int position) {
            return mPractices.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (null == convertView) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_mainactivity_list, null);
                viewHolder.title = (TextView) convertView.findViewById(R.id.title);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.title.setText(mPractices.get(position).name);
            return convertView;
        }

        class ViewHolder {
            TextView title;
        }

    }
}
