package com.nanyi545.www.memoapp.ui.main_page;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nanyi545.www.memoapp.R;
import com.nanyi545.www.memoapp.data.TotalIndex;

/**
 * Created by admin on 2018/2/11.
 */

public class TotalIndexAdapter extends RecyclerView.Adapter<TotalIndexAdapter.VH>{

    private TotalIndex data;

    public TotalIndexAdapter(TotalIndex data) {
        this.data = data;
        Log.i("aaa","size:"+getItemCount());
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_page_item, parent, false);
        VH vh = new VH(v);
        vh.contentTv= (TextView) v.findViewById(R.id.content);
        return vh;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Log.i("aaa","onBindViewHolder:"+position);
        holder.contentTv.setText(data.data.get(position).category_name);
    }

    @Override
    public int getItemCount() {
        return data.data.size();
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
