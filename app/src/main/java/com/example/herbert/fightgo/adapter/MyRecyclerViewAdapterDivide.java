package com.example.herbert.fightgo.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.herbert.fightgo.R;

import java.util.List;

import jp.wasabeef.recyclerview.animators.holder.AnimateViewHolder;

/**
 * Created by Administrator on 2018/4/14.
 */

public class MyRecyclerViewAdapterDivide extends RecyclerView.Adapter<MyRecyclerViewAdapterDivide.MyHoldDivide> implements AnimateViewHolder {
    private List<String> datas;
    private Context context;
    private View viewItem;
    public MyRecyclerViewAdapterDivide(Context context,List<String> datas) {
        this.context=context;
        this.datas = datas;
    }
    public void addItem(int position){
        datas.add(position,"NewData_"+position);
        notifyItemInserted(position);
    }
    public void removeItem(int position){
        datas.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public MyHoldDivide onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyHoldDivide(LayoutInflater.from(context).inflate(R.layout.item_recyclerview_divide,parent,false));
    }

    @Override
    public void onBindViewHolder(MyHoldDivide holder, int position) {
        holder.textView.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void preAnimateAddImpl(RecyclerView.ViewHolder holder) {
        ViewCompat.setTranslationY(viewItem, -viewItem.getHeight() * 0.3f);
        ViewCompat.setAlpha(viewItem, 0);
    }

    @Override
    public void preAnimateRemoveImpl(RecyclerView.ViewHolder holder) {

    }

    @Override
    public void animateAddImpl(RecyclerView.ViewHolder holder, ViewPropertyAnimatorListener listener) {
        ViewCompat.animate(viewItem)
                .translationY(0)
                .alpha(1)
                .setDuration(300)
                .setListener(listener)
                .start();

    }

    @Override
    public void animateRemoveImpl(RecyclerView.ViewHolder holder, ViewPropertyAnimatorListener listener) {
        ViewCompat.animate(viewItem)
                .translationY(-viewItem.getHeight() * 0.3f)
                .alpha(0)
                .setDuration(300)
                .setListener(listener)
                .start();

    }

    class MyHoldDivide extends RecyclerView.ViewHolder{
        TextView textView;
        public MyHoldDivide(View itemView) {
            super(itemView);
            viewItem=itemView;
            textView=itemView.findViewById(R.id.tv_recycler_divide);
        }
    }

}
