package com.nanyi545.www.memoapp.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2018/2/5.
 */

public class FileDownloaderTask extends AsyncTask<String ,Void ,File> {

    public interface OnDownloadCompleteListener{
        void onPostExecute(File file);
    }

    ProgressDialog progress;
    String urlStr;
    File destination;
    OnDownloadCompleteListener onDownloadCompleteListener;

    public FileDownloaderTask(ProgressDialog progress, String url, File destination,OnDownloadCompleteListener onDownloadCompleteListener) {
        this.progress = progress;
        this.urlStr = url;
        this.destination = destination;
        this.onDownloadCompleteListener=onDownloadCompleteListener;
    }

    public FileDownloaderTask(Activity host, String url, File destination,OnDownloadCompleteListener onDownloadCompleteListener) {
        this.progress = new ProgressDialog(host);
        progress.setMessage("......");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);

        this.urlStr = url;
        this.destination = destination;
        this.onDownloadCompleteListener=onDownloadCompleteListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress.show();
    }

    @Override
    protected File doInBackground(String... params) {

        try {
            Log.i("aaa","urlStr:"+urlStr);
            URL url = new URL( urlStr );
            URLConnection connection = url.openConnection();
            connection.connect();
            // this will be useful so that you can show a typical 0-100% progress bar
            int fileLength = connection.getContentLength();

            if(!destination.exists()){
                destination.getParentFile().mkdirs();
                destination.createNewFile();
            }

            // download the file
            InputStream input = new BufferedInputStream(connection.getInputStream());
            OutputStream output = new FileOutputStream(destination);

            byte data[] = new byte[1024];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                total += count;
                // publishing the progress....
//                Log.i("aaa","progress:"+(int) (total * 100 / fileLength));
                output.write(data, 0, count);
            }

            output.flush();
            output.close();
            input.close();
        } catch (IOException e) {
//            Log.i("aaa","--------task exception--------"+Log.getStackTraceString(e));
            progress.dismiss();
            e.printStackTrace();
        }
        return destination;
    }


    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        progress.dismiss();
        Log.i("aaa","file:"+file.getAbsolutePath());
        if(onDownloadCompleteListener!=null) {
            onDownloadCompleteListener.onPostExecute(file);
        }
    }


}
