package com.example.nitinsuranatask2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class homedatasetadpter extends RecyclerView.Adapter<homedatasetadpter.MyViewholder> {
    Context mContext;
    List<Upload> mData;

    public homedatasetadpter(Context mContext, List<Upload> mData) {
        this.mContext = mContext;
        this.mData = mData;

    }

    @NonNull
    @Override
    public homedatasetadpter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view= LayoutInflater.from(mContext).inflate(R.layout.postrowlayout,parent,false);
        homedatasetadpter.MyViewholder myViewholder=new homedatasetadpter.MyViewholder(view);
        return myViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull homedatasetadpter.MyViewholder holder, final int position) {
        holder.posttitle.setText(mData.get(position).getTitle());
        holder.postdes.setText(mData.get(position).getDescription());
        Picasso.get().load(mData.get(position).getUrl()).into(holder.postimg);



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public static class MyViewholder extends RecyclerView.ViewHolder{
        private TextView posttitle,postdes;
        private ImageView postimg;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            posttitle=itemView.findViewById(R.id.posttitle);
            postdes=itemView.findViewById(R.id.postdes);
            postimg=itemView.findViewById(R.id.postimage);



        }
    }

}
