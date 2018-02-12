package com.nanyi545.www.memoapp.ui.main_page;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;

import com.nanyi545.www.memoapp.data.HtmlFileList;
import com.nanyi545.www.memoapp.data.Page;
import com.nanyi545.www.memoapp.data.SubCategory;
import com.nanyi545.www.memoapp.data.TotalIndex;
import com.nanyi545.www.memoapp.ui.detail_page.PageDetailActivity;
import com.nanyi545.www.memoapp.utils.FileDownloaderTask;
import com.nanyi545.www.memoapp.utils.FileListDownTask;
import com.nanyi545.www.memoapp.utils.UrlManger;

import java.io.File;
import java.io.IOException;

/**
 * Created by admin on 2018/2/12.
 */

public class Reloader {

    TotalIndex index;
    ProgressDialog progress;
    Activity host;

    public Reloader(TotalIndex index, Activity host) {
        this.index = index;
        this.host = host;
        this.progress = new ProgressDialog(host);
        progress.setMessage("0/"+index.getTotalCount());
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
    }

    private int completedTasks=0;


    public void startReload(){
        Log.i("aaa","startReload");
        progress.show();
        int count=0;
        for(SubCategory sub:index.data){
            for (Page page:sub.category_data){
                count+=1;
                loadRemoteData(page,count);
            }
        }
    }


    private void loadRemoteData(Page page,int x){
        switch(page.getType()) {
            case Page.TYPE_WEB:
                loadRemoteWebPage(page,x);
                break;
            case Page.TYPE_TXT:
                loadRemoteTxt(page,x);
                break;
        }
    }


    private void loadRemoteWebPage(final Page page, final int x){
        FileDownloaderTask task=new FileDownloaderTask( UrlManger.getContentUrl(page), page.getFile(),
                new FileDownloaderTask.OnDownloadCompleteListener(){
                    @Override
                    public void onPostExecute(File file) {
                        String content = "IOException";
                        try {
                            content = org.apache.commons.io.FileUtils.readFileToString(file, "utf-8");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        HtmlFileList list= HtmlFileList.getInstance(content);
                        FileListDownTask task=new FileListDownTask( page.getBaseUrl(), page.getBaseFolder() , host );
                        task.execute(list.getFileArr());

                        completedTasks+=1;
                        progress.setMessage(completedTasks+"/"+index.getTotalCount());

                        if(x==index.getTotalCount()){
                            progress.dismiss();
                        }
                    }
                });
        task.execute();
    }


    private void loadRemoteTxt(final Page page, final int x){
        FileDownloaderTask task=new FileDownloaderTask( UrlManger.getContentUrl(page), page.getFile(),
                new FileDownloaderTask.OnDownloadCompleteListener(){
                    @Override
                    public void onPostExecute(File file) {
                        completedTasks+=1;
                        progress.setMessage(completedTasks+"/"+index.getTotalCount());

                        if(x==index.getTotalCount()){
                            progress.dismiss();
                        }
                    }
                });
        task.execute();
    }



}
