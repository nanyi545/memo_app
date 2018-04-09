package com.nanyi545.www.memoapp.tests;

import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue;

/**
 * Created by admin on 2018/4/9.
 */

public class LooperTest {


    public static void test(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                Looper.prepare();
                Handler handler=new Handler();
                Looper.loop();
            }
        }.start();
    }

}
