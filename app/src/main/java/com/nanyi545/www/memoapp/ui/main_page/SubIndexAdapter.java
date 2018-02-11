package com.nanyi545.www.memoapp.ui.main_page;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nanyi545.www.memoapp.R;
import com.nanyi545.www.memoapp.data.Page;
import com.nanyi545.www.memoapp.data.SubCategory;
import com.nanyi545.www.memoapp.data.TotalIndex;
import com.nanyi545.www.memoapp.ui.detail_page.PageDetailActivity;

/**
 * Created by admin on 2018/2/11.
 */

public class SubIndexAdapter extends RecyclerView.Adapter<SubIndexAdapter.VH>{

    private SubCategory data;

    public SubIndexAdapter(SubCategory data) {
        this.data = data;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_page_item, parent, false);
        VH vh = new VH(v);
        vh.contentTv= (TextView) v.findViewById(R.id.content);
        vh.holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object tag=v.getTag();
                if(tag==null) return;
                if(tag instanceof Page){
                    PageDetailActivity.start(v.getContext(), (Page) tag);
                }
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.contentTv.setText(data.category_data.get(position).toString());
        holder.holder.setTag(data.category_data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.category_data.size();
    }

    static class VH extends RecyclerView.ViewHolder{
        public VH(View itemView) {
            super(itemView);
            holder=itemView;
        }
        View holder;
        TextView contentTv;
    }

}
