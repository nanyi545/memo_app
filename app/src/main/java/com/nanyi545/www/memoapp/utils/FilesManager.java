package com.nanyi545.www.memoapp.utils;

import java.io.File;

/**
 * Created by Administrator on 2018/2/5.
 */

public class FilesManager {

    private static final String INDEX_NAME="index";
    private static File index_file;

    public static File getIndexFile(){
        if(index_file==null){
            index_file=new File(MemoApp.getInstance().getFilesDir().getAbsolutePath()+File.separator+INDEX_NAME);
        }
        return index_file;
    }





}
