package com.gejiahui.androidpractice.utils;

import android.widget.Toast;

import com.gejiahui.androidpractice.PracticeApplication;

/**
 * Created by gejiahui on 2016/5/24.
 */
public class ToastUtils {



    public static void show( CharSequence sequence) {
        Toast.makeText(PracticeApplication.getContext(), sequence, Toast.LENGTH_SHORT).show();
    }

}
