package com.nanyi545.www.memoapp.data;

import com.nanyi545.www.memoapp.utils.FilesManager;
import com.nanyi545.www.memoapp.utils.UrlManger;

import java.io.File;
import java.io.Serializable;

/**
 * Created by Administrator on 2018/2/5.
 */

public class Page implements Serializable {

    public String path;
    public String type;
    public String getType() {
        return type;
    }

    public static final String TYPE_TXT="txt";
    public static final String TYPE_IMG="png";
    public static final String TYPE_WEB="html";

    private static final String CONTENT="content";
    @Override
    public String toString() {
        switch (type) {
            case TYPE_TXT:
            case TYPE_IMG:
                return path  + CONTENT+"." + type;   //    string_related/prn_evaluator/content.txt
            case TYPE_WEB:
                return path  + CONTENT+"." + TYPE_TXT;   //    string_related/prn_evaluator/content.txt   -->  files list ...
        }
        return "";
    }

    /**
     * @return  content for {@link #TYPE_TXT}   {@link #TYPE_IMG}
     *           folder for  {@link #TYPE_WEB}
     */
    public File getFile(){
        return FilesManager.getPageFile(this);
    }

    /**
     * get content.html
     * only for  {@link #TYPE_WEB}
     * **/
    public File getContentHtmlFile(){
        return new File(getBaseFolder().getAbsolutePath()+ File.separator + CONTENT + "."+ TYPE_WEB );
    }

    /**
     * @return  base folder / base url for  {@link #TYPE_WEB}
     */
    public File getBaseFolder(){
        return getFile().getParentFile();
    }

    public String getBaseUrl(){
        return UrlManger.getBaseUrlRaw() + path ;
    }




}
