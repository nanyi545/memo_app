package com.nanyi545.www.memoapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nanyi545.www.memoapp.data.TotalIndex;
import com.nanyi545.www.memoapp.utils.FileDownloaderTask;
import com.nanyi545.www.memoapp.utils.FilesManager;
import com.nanyi545.www.memoapp.utils.UrlManger;

import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity {


    FileDownloaderTask  getIndexTask;
    TextView tv;

    TotalIndex index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv= (TextView) findViewById(R.id.hello);
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
                        tv.setText(content);
                        index=TotalIndex.getInstance(content);
                        tv.setText(index.data.get(0).category_name);
                    }
                });
        getIndexTask.execute();
    }



}
