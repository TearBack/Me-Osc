package com.test.zjj.m_osc.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.test.zjj.m_osc.ui.fragment.TweetBaseFragment;
import com.test.zjj.m_osc.utils.APIConstant;

/**
 * Created by Administrator on 2016/7/6.
 */
public class TweetPagerAdapter extends FragmentStatePagerAdapter {
    private String[] tabNames;
    public TweetPagerAdapter(FragmentManager fm,String[] tabNames) {
        super(fm);
        this.tabNames = tabNames;
    }

    @Override
    public Fragment getItem(int position) {
        TweetBaseFragment fragment = new TweetBaseFragment();
        Bundle bundle = new Bundle();
        if (position==0){
            bundle.putString("urllist", APIConstant.TWEET_LIST_NEW);
            fragment.setArguments(bundle);
        }else if (position==1){
            bundle.putString("urllist", APIConstant.TWEET_LIST_HOT);
            bundle.putBoolean("isHot",true);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabNames.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames[position];
    }
}
