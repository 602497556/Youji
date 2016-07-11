package com.zeng.youji.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16-7-11.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final int PAGE_COUNT = 3;

    private String[] tabTitles = new String[]{"游记","攻略","工具箱"};

    private List<Fragment> fragmentList = new ArrayList<>();


    public MyFragmentPagerAdapter(FragmentManager fm,List<Fragment> fragments){
        super(fm);
        this.fragmentList = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
