package com.example.shaopenglai.adapter;

import android.content.Context;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shaopenglai.R;
import com.example.shaopenglai.bean.FrescoBean;
import com.facebook.drawee.view.SimpleDraweeView;


import java.util.ArrayList;
import java.util.List;

public class FrescoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<FrescoBean.DataBean> mData;
    private Context mContext;

    public FrescoAdapter(Context mContext) {
        this.mContext = mContext;
        mData=new ArrayList<>();
    }

    public void setData(List<FrescoBean.DataBean> datas) {
        mData.clear();
        if (datas!=null){
            mData.addAll(datas);
        }
        notifyDataSetChanged();
    }

    public void addData(List<FrescoBean.DataBean> datas) {
        if (datas!=null){
            mData.addAll(datas);
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_recycle_fresco,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder= (ViewHolder) viewHolder;
        holder.simple_img.setImageURI(mData.get(i).getIcon());
        holder.simple_title.setText(mData.get(i).getName());
        holder.simple_time.setText(mData.get(i).getCreatetime());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private SimpleDraweeView simple_img;
        private TextView simple_title,simple_time;
        private SimpleDraweeView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            simple_img=itemView.findViewById(R.id.simple_img);
            simple_title=itemView.findViewById(R.id.simple_title);
            simple_time=itemView.findViewById(R.id.simple_time);
        }
    }

}
