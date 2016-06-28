package com.gejiahui.androidpractice.jni;


import com.gejiahui.androidpractice.utils.ToastUtils;

/**
 * Created by gejiahui on 2016/6/28.
 */
public class JniLibrary {

    static {
        System.loadLibrary("JniTest");
    }

    public native void getTimByJni();

    public native void getTimeStaticByJni();


    public static void JniStaticCallBack(String str){
        ToastUtils.show("jni 调用 静态方法："+str);
    }

    public void JniCallBack(String str){
        ToastUtils.show("jni 调用 方法："+str);
    }

}
