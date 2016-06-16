package com.gejiahui.androidpractice;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by gejiahui on 2016/5/24.
 */
public class PracticeApplication extends Application{
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Fresco.initialize(this);
    }

    public static Context getContext() {
        return mContext;
    }
}
