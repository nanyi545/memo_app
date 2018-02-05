package com.nanyi545.www.memoapp.data;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Administrator on 2018/2/5.
 */

public class HtmlFileList {

    public List<FileUrl>   data;

    public static class FileUrl{
        public String file;
    }


    public static HtmlFileList getInstance(String str){
        HtmlFileList list=new HtmlFileList();
        Gson gson=new Gson();
        list=gson.fromJson(str,HtmlFileList.class);
        return list;
    }

}
