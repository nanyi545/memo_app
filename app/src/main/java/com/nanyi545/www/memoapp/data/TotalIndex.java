package com.nanyi545.www.memoapp.data;

import com.google.gson.Gson;

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

}
