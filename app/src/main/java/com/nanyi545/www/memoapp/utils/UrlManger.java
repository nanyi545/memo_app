package com.nanyi545.www.memoapp.utils;

/**
 * Created by Administrator on 2018/2/5.
 */

public class UrlManger {


    private static final String BASE_URL_RAW="https://raw.githubusercontent.com/nanyi545/memo_app/master/APP_CONTENT/";

    public static String getIndexUrl(){
        return BASE_URL_RAW+"folders_list.txt";
    }

}
