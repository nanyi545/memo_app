package com.nanyi545.www.memoapp.ui.main_page;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nanyi545.www.memoapp.R;
import com.nanyi545.www.memoapp.data.TotalIndex;
import com.nanyi545.www.memoapp.tests.ThreadLocalTest;
import com.nanyi545.www.memoapp.utils.FileDownloaderTask;
import com.nanyi545.www.memoapp.utils.FilesManager;
import com.nanyi545.www.memoapp.ui.detail_page.PageDetailActivity;
import com.nanyi545.www.memoapp.utils.NetWork;
import com.nanyi545.www.memoapp.utils.UrlManger;

import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity {


    FileDownloaderTask  getIndexTask;


    RecyclerView mainRv;
    TotalIndexAdapter adapter;
    TotalIndex index;



    Reloader reloader;

    public void click(View v){
        switch(v.getId()){
            case R.id.force_reload_all:
                if(reloader!=null) reloader.startReload();
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mainRv= (RecyclerView) findViewById(R.id.main_rv);
        mainRv.setLayoutManager(new LinearLayoutManager(this));

        loadMainIndex();

        ThreadLocalTest.test();

    }


    private void loadMainIndex(){
        if(NetWork.isNetworkOnline(this)){
            loadRemote();
        } else{
            loadLocal();
        }
    }


    private void loadRemote(){
        getIndexTask=new FileDownloaderTask(this, UrlManger.getIndexUrl(), FilesManager.getIndexFile(),
                new FileDownloaderTask.OnDownloadCompleteListener(){
                    @Override
                    public void onPostExecute(File file) {
                        String content = "IOException";
                        try {
                            content = org.apache.commons.io.FileUtils.readFileToString(file, "utf-8");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        index=TotalIndex.getInstance(content);
                        adapter=new TotalIndexAdapter(index);
                        mainRv.setAdapter(adapter);
                        reloader=new Reloader(index,MainActivity.this);
                    }
                });
        getIndexTask.execute();
    }


    private void loadLocal(){
        File local=FilesManager.getIndexFile();
        String content = "IOException";
        try {
            content = org.apache.commons.io.FileUtils.readFileToString(local, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        index=TotalIndex.getInstance(content);
        adapter=new TotalIndexAdapter(index);
        mainRv.setAdapter(adapter);
        reloader=new Reloader(index,MainActivity.this);
    }





}
