package com.test.zjj.m_osc.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.zjj.m_osc.R;
import com.test.zjj.m_osc.adapter.TweetPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/7/2.
 */
public class TweetFragment extends Fragment {
    private View rootView;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private String[] tabNames = {"最新动弹", "热门动弹", "我的动弹"};
    private List<Fragment> fragments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = LayoutInflater.from(getActivity()).inflate(R.layout.frag_tweet, null);
            initView(rootView);
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    private void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.frag_tweet_vp);
        tabLayout = (TabLayout) view.findViewById(R.id.frag_tweet_tab);

        viewPager.setAdapter(new TweetPagerAdapter(getActivity().getSupportFragmentManager(), tabNames));
        tabLayout.setupWithViewPager(viewPager);
    }

}
