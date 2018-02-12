package com.nanyi545.www.memoapp.data;

import com.google.gson.Gson;
import com.nanyi545.www.memoapp.ui.detail_page.PageDetailActivity;
import com.nanyi545.www.memoapp.utils.FileDownloaderTask;
import com.nanyi545.www.memoapp.utils.FileListDownTask;
import com.nanyi545.www.memoapp.utils.UrlManger;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2018/2/5.
 */

public class TotalIndex {


    public List<SubCategory> data;

    private TotalIndex() {
    }

    public static TotalIndex getInstance(String json){
        Gson gson=new Gson();
        TotalIndex index=new TotalIndex();
        index=gson.fromJson(json,TotalIndex.class);
        return index;
    }


    public int getTotalCount(){
        int count=0;
        for(SubCategory sub:data){
            for (Page page:sub.category_data){
                count+=1;
            }
        }
        return count;
    }












}
