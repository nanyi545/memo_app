package com.nanyi545.www.memoapp.utils;

import android.app.Application;

/**
 * Created by Administrator on 2018/2/5.
 */

public class MemoApp extends Application {

    private static MemoApp app;


    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
    }


    public static MemoApp getInstance(){
        return app;
    }


}
