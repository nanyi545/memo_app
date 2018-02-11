package com.nanyi545.www.memoapp.ui.detail_page;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.nanyi545.www.memoapp.R;
import com.nanyi545.www.memoapp.data.HtmlFileList;
import com.nanyi545.www.memoapp.data.Page;
import com.nanyi545.www.memoapp.utils.FileDownloaderTask;
import com.nanyi545.www.memoapp.utils.FileListDownTask;
import com.nanyi545.www.memoapp.utils.NetWork;
import com.nanyi545.www.memoapp.utils.UrlManger;

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
    TextView txtTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page= (Page) getIntent().getSerializableExtra(PAGE_KEY);
        setContentView(R.layout.activity_page_detail);
        mWebview= (WebView) findViewById(R.id.m_webview);
        setUpView();

        if(NetWork.isNetworkOnline(this)){
            loadRemoteData();
        } else {
            loadLocalData();
        }

    }



    private void setUpView(){
        switch(page.getType())
        {
            case Page.TYPE_WEB:
                mWebview.setVisibility(View.VISIBLE);
                mWebview.setWebViewClient(new WebViewClient());
                mWebview.setWebChromeClient(new WebChromeClient());  // this is needed to trigger alert !!
                WebSettings webSettings = mWebview.getSettings();
                webSettings.setJavaScriptEnabled(true);
                break;
            case Page.TYPE_TXT:
                findViewById(R.id.text_holer).setVisibility(View.VISIBLE);
                txtTv= (TextView) findViewById(R.id.text_content);
                break;
        }
    }



    private void loadRemoteData(){
        switch(page.getType()) {
            case Page.TYPE_WEB:
                loadRemoteWebPage();
                break;
            case Page.TYPE_TXT:
                loadRemoteTxt();
                break;
        }
    }



    private void loadRemoteWebPage(){
        task=new FileDownloaderTask(this, UrlManger.getContentUrl(page), page.getFile(),
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
                        FileListDownTask task=new FileListDownTask( page.getBaseUrl(), page.getBaseFolder() , PageDetailActivity.this , mWebview);
                        task.execute(list.getFileArr());
                    }
                });
        task.execute();
    }


    private void loadRemoteTxt(){
        task=new FileDownloaderTask(this, UrlManger.getContentUrl(page), page.getFile(),
                new FileDownloaderTask.OnDownloadCompleteListener(){
                    @Override
                    public void onPostExecute(File file) {
                        String content = "IOException";
                        try {
                            content = org.apache.commons.io.FileUtils.readFileToString(file, "utf-8");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        txtTv.setText(content);
                    }
                });
        task.execute();
    }







    private void loadLocalData(){
        switch(page.getType())
        {
            case Page.TYPE_WEB:
                loadLocalWebpage();
                break;
            case Page.TYPE_TXT:
                loadLocalTxt();
                break;
        }
    }


    private void loadLocalWebpage(){
        mWebview.loadUrl("file:///" + page.getContentHtmlFile().getAbsolutePath());
    }

    private void loadLocalTxt(){

    }


}
