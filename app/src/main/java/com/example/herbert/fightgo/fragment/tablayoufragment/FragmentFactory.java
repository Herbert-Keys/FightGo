package com.example.herbert.fightgo.fragment.tablayoufragment;

import android.support.v4.app.Fragment;

import com.example.herbert.fightgo.fragment.bottomnavigationfragment.F1;
import com.example.herbert.fightgo.fragment.bottomnavigationfragment.F2;
import com.example.herbert.fightgo.fragment.bottomnavigationfragment.F3;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/3/29.
 */

public class FragmentFactory {
    private static HashMap<Integer, Fragment> mBaseFragments = new HashMap<Integer, Fragment>();

    public static Fragment createFragment(int pos) {
        Fragment baseFragment = mBaseFragments.get(pos);

        if (baseFragment == null) {
            switch (pos) {
                case 0:
                    baseFragment = new F1();//头条
                    break;
                case 1:
                    baseFragment = new F2();//要闻
                    break;
                case 2:
                    baseFragment = new F3();//娱乐
                    break;


            }
            mBaseFragments.put(pos, baseFragment);
        }
        return baseFragment;
    }
}
