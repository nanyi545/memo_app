package com.nanyi545.www.memoapp.ui.main_page;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nanyi545.www.memoapp.R;
import com.nanyi545.www.memoapp.data.SubCategory;

public class SubActivity extends Activity {


    RecyclerView subRv;
    SubCategory data;
    private static final String DATA_KEY="data_key";

    public static void start(SubCategory data,Context c){
        Intent i=new Intent(c,SubActivity.class);
        i.putExtra(DATA_KEY,data);
        c.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data= (SubCategory) getIntent().getSerializableExtra(DATA_KEY);
        setContentView(R.layout.activity_sub);
        subRv= (RecyclerView) findViewById(R.id.sub_rv);
        subRv.setLayoutManager(new LinearLayoutManager(this));
        subRv.setAdapter(new SubIndexAdapter(data));
    }



}
