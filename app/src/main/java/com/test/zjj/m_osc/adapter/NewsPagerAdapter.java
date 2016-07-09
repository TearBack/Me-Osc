package com.test.zjj.m_osc.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/7/3.
 */
public class NewsPagerAdapter extends FragmentStatePagerAdapter {
    private String[] mStrs;
    private List<Fragment> mFragments;
    public NewsPagerAdapter(FragmentManager fm, String[] strs, List<Fragment> fragments) {
        super(fm);
        mStrs = strs;
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStrs[position];
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.i("testi", "LineNum:39-->NewsPagerAdapter-->instantiateItem: :" + position);
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.i("testi", "LineNum:46-->NewsPagerAdapter-->destroyItem: po:" + position);
        super.destroyItem(container, position, object);
    }
}
