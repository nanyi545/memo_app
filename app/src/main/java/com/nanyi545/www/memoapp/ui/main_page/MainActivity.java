package com.nanyi545.www.memoapp.ui.main_page;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nanyi545.www.memoapp.R;
import com.nanyi545.www.memoapp.data.TotalIndex;
import com.nanyi545.www.memoapp.utils.FileDownloaderTask;
import com.nanyi545.www.memoapp.utils.FilesManager;
import com.nanyi545.www.memoapp.ui.detail_page.PageDetailActivity;
import com.nanyi545.www.memoapp.utils.UrlManger;

import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity {


    FileDownloaderTask  getIndexTask;


    RecyclerView mainRv;
    TotalIndexAdapter adapter;
    TotalIndex index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainRv= (RecyclerView) findViewById(R.id.main_rv);
        mainRv.setLayoutManager(new LinearLayoutManager(this));

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
                    }
                });
        getIndexTask.execute();
    }


    public void test(View view){
        PageDetailActivity.start(view.getContext(),index.data.get(1).category_data.get(0));
    }


}
