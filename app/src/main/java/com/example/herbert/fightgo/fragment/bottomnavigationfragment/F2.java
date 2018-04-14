package com.example.herbert.fightgo.fragment.bottomnavigationfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;

import com.example.herbert.fightgo.R;
import com.example.herbert.fightgo.adapter.MyRecyclerViewAdapterDivide;
import com.example.herbert.fightgo.utils.DensityUtil;
import com.example.herbert.fightgo.view.CustomDecoration;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;

/**
 * Created by Administrator on 2018/3/29.
 */

public class F2 extends Fragment implements View.OnClickListener {
    RecyclerView recyclerView;
    private List<String> list=new ArrayList<>();
    private Button bt_add;
    private Button bt_remove;
    MyRecyclerViewAdapterDivide adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.f2, container, false);
        recyclerView=v.findViewById(R.id.recy_divide);
        bt_add=v.findViewById(R.id.bt_add);
        bt_remove=v.findViewById(R.id.bt_remove);
        bt_add.setOnClickListener(this);
        bt_remove.setOnClickListener(this);
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(gridLayoutManager);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getData();
        recyclerView.addItemDecoration(new CustomDecoration(getActivity(),CustomDecoration.VERTICAL_LIST,R.drawable.recyclerview_divide, DensityUtil.dp2px(getActivity(),15)));
        recyclerView.addItemDecoration(new CustomDecoration(getActivity(),CustomDecoration.HORIZONTAL_LIST,R.drawable.recyclerview_divide, DensityUtil.dp2px(getActivity(),5)));
        adapter= new MyRecyclerViewAdapterDivide(getActivity(),list);
        ScaleInAnimationAdapter alphaAdapter = new ScaleInAnimationAdapter(adapter);
        alphaAdapter.setDuration(2000);
        alphaAdapter.setFirstOnly(false);
        alphaAdapter.setInterpolator(new OvershootInterpolator());
        recyclerView.setAdapter(new SlideInBottomAnimationAdapter(alphaAdapter));

        //recyclerView.setAdapter(new MyRecyclerViewAdapterDivide(getActivity(),list));
    }

    private void getData() {
        for(int i=0;i<150;i++){
            list.add("我是第"+i+"个数据");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_add:
                adapter.addItem(5);
                break;
            case R.id.bt_remove:
                adapter.removeItem(5);
                break;
        }
    }
}
