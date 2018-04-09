package com.nanyi545.www.memoapp.tests;

import android.os.Handler;
import android.os.MessageQueue;
import android.util.Log;

/**
 * Created by admin on 2018/4/9.
 */

public class ThreadLocalTest {

    private static final String TAG="THREAD_LOCAL_TEST";

    private static ThreadLocal<Boolean> mBoolThreadLocal=new ThreadLocal<>();

    public static void test(){
        new Thread("thread1"){
            @Override
            public void run() {
                super.run();
                mBoolThreadLocal.set(false);
                Log.i(TAG,"thread1  saved value:"+mBoolThreadLocal.get());
            }
        }.start();
        new Thread("thread2"){
            @Override
            public void run() {
                super.run();
                Log.i(TAG,"thread2  saved value:"+mBoolThreadLocal.get());
            }
        }.start();
    }



}
