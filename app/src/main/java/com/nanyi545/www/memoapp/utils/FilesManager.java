package com.nanyi545.www.memoapp.utils;

import com.nanyi545.www.memoapp.data.Page;

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


    private static final String DATA_FOLDER_NAME="data_folder";
    public static File getPageFile(Page page){
        File file=new File(MemoApp.getInstance().getFilesDir().getAbsolutePath() + File.separator  + DATA_FOLDER_NAME + File.separator + page.toString());
        return file;
    }



}
