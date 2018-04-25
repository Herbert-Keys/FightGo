package com.example.herbert.fightgo.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.herbert.fightgo.R;
import com.example.herbert.fightgo.adapter.MyPopRecyAdapter;
import com.example.herbert.fightgo.fragment.tablayoufragment.FragmentFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TabLayoutActivity extends AppCompatActivity {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.img_add)
    ImageView imgAdd;
    String[] strs = new String[]{"Fragment1", "Fragment2", "Fragment3", "Fragment4", "Fragment5", "Fragment6", "Fragment7", "Fragment8"};
    @BindView(R.id.ll_pop)
    LinearLayout llPop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        ButterKnife.bind(this);
        vp.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tablayout.setupWithViewPager(vp);

    }

    @OnClick(R.id.img_add)
    public void onViewClicked() {

        View view = LayoutInflater.from(this).inflate(R.layout.pop_recy, null);
        RecyclerView popRecy = view.findViewById(R.id.pop_recy);
        popRecy.setLayoutManager(new GridLayoutManager(this, 4));
        popRecy.setAdapter(new MyPopRecyAdapter(strs, this));

        PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT, false);
        // 需要设置一下此参数，点击外边可消失
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
        // 设置点击窗口外边窗口消失
        popupWindow.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        popupWindow.setFocusable(true);

        popupWindow.showAsDropDown(llPop, 0, 0);
    }

    class MyAdapter extends FragmentPagerAdapter {


        public MyAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return strs[position];
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.createFragment(position);
        }

        @Override
        public int getCount() {
            return strs.length;
        }
    }


}
