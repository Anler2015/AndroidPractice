package com.gejiahui.androidpractice.utils;

import android.content.Context;

/**
 * Created by gejiahui on 2016/5/24.
 */
public class DimenUtils {

    public static int dip2px(Context context, float dpValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
