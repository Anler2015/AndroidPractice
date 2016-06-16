package com.gejiahui.androidpractice.retrofitdemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gejiahui.androidpractice.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by gejiahui on 2016/6/15.
 */
public class RetrofitActivity extends AppCompatActivity {
    private static final String TAG = "RetrofitActivity";
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.get)
    Button mButton;

    List<GankInfo> mData = new ArrayList<GankInfo>();
    RetrofitAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.get)
    public void onClick() {
        getGankInfo();
    }

    private void getGankInfo() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/data/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()) // GSON进行转换
                .build();

        GankAPIService apiStores = retrofit.create(GankAPIService.class);
        Observable<GankResult> observable = apiStores.getGankInfo("福利", 10, 1);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<GankResult, List<GankInfo>>() {
                    @Override
                    public List<GankInfo> call(GankResult gankResult) {
                        List<GankInfo> items = gankResult.results;
                        return items;
                    }
                    }).subscribe(new Observer<List<GankInfo>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(List<GankInfo> gankInfos) {
                            mData = gankInfos;
                            mAdapter = new RetrofitAdapter();
                            mRecyclerView.setAdapter(mAdapter);
                            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

                        }
                    });

    }


    public class RetrofitAdapter extends RecyclerView.Adapter<RetrofitAdapter.RetrofitVH>{


        @Override
        public RetrofitVH onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_retrofit_recycler_view,parent,false);
            return new RetrofitVH(view);
        }

        @Override
        public void onBindViewHolder(RetrofitVH holder, int position) {
            holder.img.setImageURI(Uri.parse(mData.get(position).url));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class RetrofitVH extends RecyclerView.ViewHolder{
            @Bind(R.id.img)
            SimpleDraweeView img;
            public RetrofitVH(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }

}
