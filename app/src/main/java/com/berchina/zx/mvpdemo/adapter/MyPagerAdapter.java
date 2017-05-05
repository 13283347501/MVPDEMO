package com.berchina.zx.mvpdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by zx on 2017/5/5 10:40
 * 项目名称：MVPDEMO
 * 类描述：
 * 备注
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> mFragments;
    String[] mTitles;

    public MyPagerAdapter(List<Fragment> mFragments,String[] mTitles, FragmentManager fm) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles=mTitles;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
}
