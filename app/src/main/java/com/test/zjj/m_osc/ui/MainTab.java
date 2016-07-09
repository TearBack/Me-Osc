package com.test.zjj.m_osc.ui;

import com.test.zjj.m_osc.R;
import com.test.zjj.m_osc.ui.fragment.DiscoveryFragment;
import com.test.zjj.m_osc.ui.fragment.MeFragment;
import com.test.zjj.m_osc.ui.fragment.TweetFragment;
import com.test.zjj.m_osc.ui.fragment.NewsFragment;

/**
 * Created by Administrator on 2016/7/3.
 */
public enum MainTab {
    NEWS(0, R.string.main_tab_name_news, R.drawable.selector_ic_nav_news, NewsFragment.class),
    TWEET(1, R.string.main_tab_name_tweet, R.drawable.selector_ic_nav_tweet, TweetFragment.class),
    QUICK(2, R.string.main_tab_name_quick, R.drawable.selector_ic_nav_quick,null),
    DISCOVERY(3, R.string.main_tab_name_discovery, R.drawable.selector_ic_nav_discovery, DiscoveryFragment.class),
    ME(4, R.string.main_tab_name_me, R.drawable.selector_ic_nav_me, MeFragment.class);


    private int idx;
    private int resName;
    private int resIcon;
    private Class<?> clz;

    private MainTab(int idx, int resName, int resIcon, Class<?> clz) {
        this.idx = idx;
        this.resName = resName;
        this.resIcon = resIcon;
        this.clz = clz;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getResName() {
        return resName;
    }

    public void setResName(int resName) {
        this.resName = resName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }
}

