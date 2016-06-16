package com.gejiahui.androidpractice.retrofitdemo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by gejiahui on 2016/6/15.
 */
public interface GankAPIService {

    @GET("{type}/{num}/{page}")
    Observable<GankResult> getGankInfo(@Path("type") String type, @Path("num") int num, @Path("page") int page);


}
