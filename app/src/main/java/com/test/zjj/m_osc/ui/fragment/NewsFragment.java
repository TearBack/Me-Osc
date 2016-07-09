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
import com.test.zjj.m_osc.adapter.NewsPagerAdapter;
import com.test.zjj.m_osc.utils.APIConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/2.
 */
public class NewsFragment extends Fragment {

    private String[] titles = new String[]{"资讯", "博客", "问答", "活动"};

    private TabLayout mTab;
    private ViewPager mViewPager;
    private List<Fragment> mFragments;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.frag_news, null);
            mViewPager = (ViewPager) rootView.findViewById(R.id.frag_news_vp);
            mTab = (TabLayout) rootView.findViewById(R.id.frag_news_tab);

            initView();
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
//        View view = inflater.inflate(R.layout.frag_news,null);




        return rootView;
    }

    private void initView() {
        initFragments();
        mViewPager.setAdapter(new NewsPagerAdapter(getActivity().getSupportFragmentManager(), titles, mFragments));
        mTab.setupWithViewPager(mViewPager);
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            News_zxFragment newszxFragment = new News_zxFragment();
            if (i == 0) {
                Bundle bundle = new Bundle();
                bundle.putString("url_list", APIConstant.NEWS_LIST);
                bundle.putString("url_detali", APIConstant.NEWS_DETAIL);
                bundle.putInt("state",APIConstant.FRAG_ZX);
                newszxFragment.setArguments(bundle);
            }else if (i==1){
                Bundle bundle = new Bundle();
                bundle.putString("url_list", APIConstant.NEWS_BLOGLIST);
                bundle.putString("url_detali", APIConstant.NEWS_BLOGDETAIL);
                bundle.putInt("state",APIConstant.FRAG_BLOG);
                newszxFragment.setArguments(bundle);
            }
            mFragments.add(newszxFragment);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
