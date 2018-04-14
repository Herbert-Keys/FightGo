package com.example.herbert.fightgo.fragment.bottomnavigationfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.herbert.fightgo.R;

/**
 * Created by Administrator on 2018/3/29.
 */

public class F3 extends Fragment {
    private TextView textView;
    private Button add;
    private Button cancle;
    private Button update;
    private Button query;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.f3, container, false);
        textView=v.findViewById(R.id.tv_f3);
        add=v.findViewById(R.id.dao_add);
        cancle=v.findViewById(R.id.dao_cancel);
        update=v.findViewById(R.id.dao_update);
        query=v.findViewById(R.id.dao_query);
        return v;
    }
}
