package com.example.herbert.fightgo.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.herbert.fightgo.Model.RecyclerItemModel;
import com.example.herbert.fightgo.R;

import java.util.List;

/**
 * Created by Administrator on 2018/3/29.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyHolder> {
    private List<RecyclerItemModel> data;
    private Context context;
    public OnRecyclerViewItemClickListenner onRecyclerViewItemClickListenner;

    public void setOnRecyclerViewItemClickListenner(OnRecyclerViewItemClickListenner onRecyclerViewItemClickListenner) {
        this.onRecyclerViewItemClickListenner = onRecyclerViewItemClickListenner;
    }

    public MyRecyclerViewAdapter(List<RecyclerItemModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.item_recyclerview,parent,false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
            holder.textView.setText(data.get(position).getTitle());
            holder.imageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),data.get(position).getBitmap()));
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRecyclerViewItemClickListenner.onItemClick(view,position);
                }
            });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        CardView cardView;
        public MyHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.tv_recycler);
            imageView=itemView.findViewById(R.id.img_recycler);
            cardView=itemView.findViewById(R.id.cv);
        }

    }

    public interface OnRecyclerViewItemClickListenner{
        void onItemClick(View view,int position);
    }
}
