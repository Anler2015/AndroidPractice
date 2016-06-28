//
// Created by gejiahui on 2016/6/28.
//

#include "com_gejiahui_androidpractice_jni_JniLibrary.h"
#include <stdio.h>
#include <time.h>
JNIEXPORT void JNICALL Java_com_gejiahui_androidpractice_jni_JniLibrary_getTimByJni
(JNIEnv *env, jobject obj){

    jclass clazz = env->GetObjectClass(obj);

    jmethodID func = env->GetMethodID(clazz,"JniCallBack","(Ljava/lang/String;)V");
    if(func == NULL){
        printf(" can't  find method !");
    }

    time_t t = time(0);
    char tmp[64];
    strftime( tmp, sizeof(tmp), "time now: ,%Y/%m/%d %X %A 本年第%j天",localtime(&t));

    jstring time = env->NewStringUTF(tmp);

    env->CallVoidMethod(obj,func,time);
}


JNIEXPORT void JNICALL Java_com_gejiahui_androidpractice_jni_JniLibrary_getTimeStaticByJni
(JNIEnv *env, jobject obj){

    jclass clazz = env->GetObjectClass(obj);

    jmethodID func = env->GetStaticMethodID(clazz,"JniStaticCallBack","(Ljava/lang/String;)V");
    if(func == NULL){
    printf(" can't  find method !");
    }

    time_t t = time(0);
    char tmp[64];
    strftime( tmp, sizeof(tmp), "time now: ,%Y/%m/%d %X %A 本年第%j天",localtime(&t));
    jstring time = env->NewStringUTF(tmp);

    env->CallStaticVoidMethod(clazz,func,time);
}