package com.example.herbert.fightgo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.herbert.fightgo.R;

/**
 * Created by Administrator on 2018/4/23.
 */

public class MyPopRecyAdapter extends RecyclerView.Adapter<MyPopRecyAdapter.MyPopRecyHolder>{
    private String[] datas;
    private Context context;

    public MyPopRecyAdapter(String[] datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public MyPopRecyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyPopRecyHolder(LayoutInflater.from(context).inflate(R.layout.pop_recy_item,parent,false));
    }

    @Override
    public void onBindViewHolder(MyPopRecyHolder holder, int position) {
        holder.textView.setText(datas[position]);
    }

    @Override
    public int getItemCount() {
        return datas.length;
    }

    class MyPopRecyHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public MyPopRecyHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.tv_recy_pop_item);
        }
    }
}
