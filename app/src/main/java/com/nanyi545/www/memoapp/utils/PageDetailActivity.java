package com.nanyi545.www.memoapp.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.nanyi545.www.memoapp.R;
import com.nanyi545.www.memoapp.data.HtmlFileList;
import com.nanyi545.www.memoapp.data.Page;
import com.nanyi545.www.memoapp.data.TotalIndex;

import java.io.File;
import java.io.IOException;

public class PageDetailActivity extends AppCompatActivity {

    private static final String PAGE_KEY="page_key";

    public static void start(Context c,Page page){
        Intent i=new Intent(c,PageDetailActivity.class);
        i.putExtra(PAGE_KEY,page);
        c.startActivity(i);
    }

    Page page;
    FileDownloaderTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page= (Page) getIntent().getSerializableExtra(PAGE_KEY);
        setContentView(R.layout.activity_page_detail);
        Log.i("aaa","--------[][][]----------"+UrlManger.getHtmlPageIndexUrl(page)+"   }} "+page.getFile().getAbsolutePath());
        task=new FileDownloaderTask(this, UrlManger.getHtmlPageIndexUrl(page), page.getFile(),
                new FileDownloaderTask.OnDownloadCompleteListener(){
                    @Override
                    public void onPostExecute(File file) {
                        String content = "IOException";
                        try {
                            content = org.apache.commons.io.FileUtils.readFileToString(file, "utf-8");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.i("aaa","------------------"+content);

                        HtmlFileList list= HtmlFileList.getInstance(content);

                        Log.i("aaa","--"+list.data.get(0).file);
                        Log.i("aaa","--"+list.data.get(1).file);
                        Log.i("aaa","--"+list.data.get(2).file);

                    }
                });

        task.execute();
    }




}
