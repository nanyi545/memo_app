package com.nanyi545.www.memoapp.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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

    WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page= (Page) getIntent().getSerializableExtra(PAGE_KEY);
        setContentView(R.layout.activity_page_detail);
        mWebview= (WebView) findViewById(R.id.m_webview);
        mWebview.setWebViewClient(new WebViewClient());
        mWebview.setWebChromeClient(new WebChromeClient());  // this is needed to trigger alert !!

        WebSettings webSettings = mWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);



        task=new FileDownloaderTask(this, UrlManger.getHtmlPageIndexUrl(page), page.getFile(),
                new FileDownloaderTask.OnDownloadCompleteListener(){
                    @Override
                    public void onPostExecute(File file) {
                        String content = "IOException";
                        try {
                            content = org.apache.commons.io.FileUtils.readFileToString(file, "utf-8");
                        } catch (IOException e) {
                            e.printStackTrace();
//                            Log.i("aaa","------------------"+Log.getStackTraceString(e));
                        }

                        HtmlFileList list= HtmlFileList.getInstance(content);

                        FileListDownTask task=new FileListDownTask( page.getBaseUrl(), page.getBaseFolder() , PageDetailActivity.this , mWebview);
                        task.execute(list.getFileArr());

                    }
                });

        task.execute();




    }




}
