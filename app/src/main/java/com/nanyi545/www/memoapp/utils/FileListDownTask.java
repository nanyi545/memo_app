package com.nanyi545.www.memoapp.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by admin on 2018/2/6.
 */

public class FileListDownTask extends AsyncTask <String,Void,String> {

    private String baseUrl;

    private File holder;
    private File htmlFile;
    private int count;

    ProgressDialog progress;
    private WebView targetWV;


    public FileListDownTask(String baseUrl, File holder, Activity host , WebView webview) {
        this.baseUrl = baseUrl;
        this.holder = holder;
        this.progress = new ProgressDialog(host);
        progress.setMessage("");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        targetWV=webview;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress.show();
    }

    @Override
    protected String doInBackground(String... params) {

        count = params.length;
        for (String str:params){
            downFile(str);
        }

        return "";
    }


    private int currentDownIndex=1;

    private void downFile(String fileName){

        String urlStr =baseUrl+fileName;
        String file=holder.getAbsolutePath()+ File.separator + fileName;
        File destination=new File( file );

        if(file.endsWith("html")){
            htmlFile=destination;
        }

        try {
            URL url = new URL( urlStr );
            URLConnection connection = url.openConnection();
            connection.connect();


            if(!destination.exists()){
                if(! holder.exists() ){
                    destination.getParentFile().mkdirs();
                }
                destination.createNewFile();
            }

            // download the file
            InputStream input = new BufferedInputStream(connection.getInputStream());
            OutputStream output = new FileOutputStream(destination);

            byte data[] = new byte[1024];

            int count;
            while ((count = input.read(data)) != -1) {
                output.write(data, 0, count);
            }

            output.flush();
            output.close();
            input.close();
        } catch (IOException e) {
            progress.dismiss();
            e.printStackTrace();
        }


    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progress.dismiss();
        Log.i("aaa","onPostExecute      file:///" + htmlFile.getAbsolutePath());
        targetWV.loadUrl("file:///" + htmlFile.getAbsolutePath());
    }



}
