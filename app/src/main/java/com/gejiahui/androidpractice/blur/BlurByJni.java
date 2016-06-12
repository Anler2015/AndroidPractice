package com.gejiahui.androidpractice.blur;



/**
 * Created by gejiahui on 2016/6/8.
 */
public class BlurByJni {

    static {
        System.loadLibrary("blur");
    }

    public native int[] blurByJni(int[] pix,int w,int h, int radius);

}
