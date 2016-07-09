package com.test.zjj.m_osc.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.test.zjj.m_osc.R;
import com.test.zjj.m_osc.adapter.NewsZxListAdapter;
import com.test.zjj.m_osc.bean.NewsList;
import com.test.zjj.m_osc.ui.activity.BlogDetailActivity;
import com.test.zjj.m_osc.ui.activity.ZxDetail;
import com.test.zjj.m_osc.utils.APIConstant;
import com.test.zjj.m_osc.utils.JSONUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/7/3.
 */
public class News_zxFragment extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate, AdapterView.OnItemClickListener {


    private BGARefreshLayout bga;
    private ListView lv;
    private int curnindx = 1;
    private String mUrl_list;
    private String mUrl_detali;
    private List<NewsList> mList;
    private NewsZxListAdapter mAdapter;
    private final int First = 0;
    private final int Refresh = 1;
    private final int LoadMore = 2;
    private int FRAG_STATE = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_base_listview, null);
        initView(view);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mUrl_list = bundle.getString("url_list");
            mUrl_detali = bundle.getString("news_detail");
            FRAG_STATE = bundle.getInt("state");
        }

        if (savedInstanceState != null && savedInstanceState.getParcelableArrayList("datalist") != null) {
            mList = savedInstanceState.getParcelableArrayList("datalist");
            lv.setAdapter(mAdapter = new NewsZxListAdapter(mList,FRAG_STATE));
            lv.setOnItemClickListener(this);
        } else {
            getDataListFromNet(First, FRAG_STATE);
        }

        return view;
    }

    private void getDataListFromNet(final int state, final int frag) {

        if (mUrl_list != null)
            switch (state) {
                case First:
                case Refresh:
                    curnindx = 1;
                    OkHttpUtils.get().url(String.format(mUrl_list, curnindx)).build().execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            List<NewsList> tem = JSONUtils.parse2newslist(response, frag);
                            if (tem != null && tem.size() > 0) {
                                if (state == First) {
                                    mList = tem;
                                    mAdapter = new NewsZxListAdapter(mList,FRAG_STATE);
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (mAdapter != null) {
                                                lv.setAdapter(mAdapter);
                                                lv.setOnItemClickListener(News_zxFragment.this);
                                            }
                                        }
                                    });
                                } else {
                                    mList.clear();
                                    mList.addAll(tem);
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (mAdapter != null) mAdapter.notifyDataSetChanged();
                                            Toast.makeText(getActivity(), "刷新完成", Toast.LENGTH_SHORT).show();
                                            bga.endRefreshing();
                                        }
                                    });
                                }

                            }
                        }
                    });
                    break;
                case LoadMore:
                    curnindx++;
                    OkHttpUtils.get().url(String.format(mUrl_list, curnindx)).build().execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if (!TextUtils.isEmpty(response)) {
                                List<NewsList> tem = JSONUtils.parse2newslist(response, frag);
                                if (tem != null && tem.size() > 0) {
                                    mList.addAll(tem);
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (mAdapter != null) {
                                                mAdapter.notifyDataSetChanged();
                                                Toast.makeText(getActivity(), "加载完成", Toast.LENGTH_SHORT).show();
                                                bga.endLoadingMore();
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    });
                    break;
            }

    }

    private void initView(View mView) {
        bga = (BGARefreshLayout) mView.findViewById(R.id.frag_base_bga);
        lv = (ListView) mView.findViewById(R.id.frag_base_lv);

        // 为BGARefreshLayout设置代理
        bga.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getActivity(), true);
        // 设置下拉刷新和上拉加载更多的风格
        bga.setRefreshViewHolder(refreshViewHolder);
        //        bga.setCustomHeaderView();

        // 为了增加下拉刷新头部和加载更多的通用性，提供了以下可选配置选项  -------------START
        // 设置正在加载更多时不显示加载更多控件
        // mRefreshLayout.setIsShowLoadingMoreView(false);
        //        // 设置正在加载更多时的文本
        //        refreshViewHolder.setLoadingMoreText(loadingMoreText);
        //        // 设置整个加载更多控件的背景颜色资源id
        //        refreshViewHolder.setLoadMoreBackgroundColorRes(loadMoreBackgroundColorRes);
        //        // 设置整个加载更多控件的背景drawable资源id
        //        refreshViewHolder.setLoadMoreBackgroundDrawableRes(loadMoreBackgroundDrawableRes);
        //        // 设置下拉刷新控件的背景颜色资源id
        //        refreshViewHolder.setRefreshViewBackgroundColorRes(refreshViewBackgroundColorRes);
        //        // 设置下拉刷新控件的背景drawable资源id
        //        refreshViewHolder.setRefreshViewBackgroundDrawableRes(refreshViewBackgroundDrawableRes);
        //        // 设置自定义头部视图（也可以不用设置）     参数1：自定义头部视图（例如广告位）， 参数2：上拉加载更多是否可用
        //        mRefreshLayout.setCustomHeaderView(mBanner, false);
        //        // 可选配置  -------------EN


    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        //下拉刷新
        if (mList != null && mList.size() > 0) {
            getDataListFromNet(Refresh, FRAG_STATE);
        }
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        //上拉加载
        if (mList != null && mList.size() > 0) {
            getDataListFromNet(LoadMore, FRAG_STATE);
        }
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mList != null && mList.size() > 0) {
            outState.putParcelableArrayList("datalist", (ArrayList<? extends Parcelable>) mList);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //item点击事件
        if (FRAG_STATE == APIConstant.FRAG_ZX) {
            Intent intent = new Intent(getActivity(), ZxDetail.class);
            intent.putExtra("zxid", mList.get(position).getId());
            startActivity(intent);
        }else if (FRAG_STATE==APIConstant.FRAG_BLOG){
            Intent intent = new Intent(getActivity(), BlogDetailActivity.class);
            intent.putExtra("blid",mList.get(position).getId());
            intent.putExtra("type",mList.get(position).getType());
            startActivity(intent);
        }
    }
}
