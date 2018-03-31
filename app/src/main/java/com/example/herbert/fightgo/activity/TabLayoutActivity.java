package com.example.herbert.fightgo.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.herbert.fightgo.R;
import com.example.herbert.fightgo.fragment.tablayoufragment.FragmentFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabLayoutActivity extends AppCompatActivity {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.vp)
    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        ButterKnife.bind(this);
        vp.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tablayout.setupWithViewPager(vp);

    }
    class  MyAdapter extends FragmentPagerAdapter{
        String[] strs;
        public MyAdapter(FragmentManager fm) {
            super(fm);
            strs =getResources().getStringArray(R.array.tab_arrs);
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
