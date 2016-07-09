package com.test.zjj.m_osc.ui.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.test.zjj.m_osc.R;
import com.test.zjj.m_osc.adapter.TweetListAdapter;
import com.test.zjj.m_osc.bean.TweetListBean;
import com.test.zjj.m_osc.utils.APIConstant;
import com.test.zjj.m_osc.utils.JSONUtils;
import com.test.zjj.m_osc.utils.Network;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/7/6.
 */
public class TweetBaseFragment extends Fragment implements SwipyRefreshLayout.OnRefreshListener {

    private SwipyRefreshLayout refreshLayout;
    private ListView listView;
    private String urlList;
    private String urlDetail = APIConstant.TWEET_LIST_DETAIL;
    private List<TweetListBean> listBean;
    private TweetListAdapter adapter;
    private final int FIRSTLOAD = 0;
    private final int REFRESH = 1;
    private final int LOADMORE = 2;
    private int page = 1;
    private boolean isHot;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_tweet_base, null);
        initView(view);

        Bundle bundle = getArguments();
        if (bundle != null) {
            urlList = bundle.getString("urllist");
            isHot = bundle.getBoolean("isHot");
        }
        if (savedInstanceState != null
                && savedInstanceState.getParcelableArrayList("data") != null
                && savedInstanceState.getParcelableArrayList("data").size() > 0) {
            listBean = savedInstanceState.getParcelableArrayList("data");
            adapter = new TweetListAdapter(listBean, getActivity());
            listView.setAdapter(adapter);
        } else if (Network.isConnected(getContext())) {
            getDataFromNet(FIRSTLOAD);
        } else {
            Toast.makeText(getActivity(), "没有网络", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    private void getDataFromNet(final int state) {
        switch (state) {
            case FIRSTLOAD:
            case REFRESH:
                if (!TextUtils.isEmpty(urlList)) {
                    page = 1;
                    String format = String.format(urlList, page);
                    OkHttpUtils.get().url(format).build().execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e("teste", "onError: okhhtp-TweetBasefragment");
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if (!TextUtils.isEmpty(response))
                                new AsyncTask<String, Void, List<TweetListBean>>() {
                                    @Override
                                    protected List<TweetListBean> doInBackground(String... params) {
                                        List<TweetListBean> tem = JSONUtils.parse2TweetList(params[0]);
                                        return tem;
                                    }

                                    @Override
                                    protected void onPostExecute(List<TweetListBean> tweetListBeen) {
                                        if (state == FIRSTLOAD) {
                                            listBean = tweetListBeen;
                                            if (listBean != null && listBean.size() > 0) {
                                                adapter = new TweetListAdapter(listBean, getActivity());
                                                if (adapter != null)
                                                    listView.setAdapter(adapter);
                                            }
                                        } else {
                                            if (listBean == null && adapter == null) return;
                                            listBean.clear();
                                            listBean.addAll(tweetListBeen);
                                            adapter.notifyDataSetChanged();
                                            refreshLayout.setRefreshing(false);
                                            Toast.makeText(getActivity(), "刷新成功", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }.execute(response);
                        }
                    });
                }
                break;
            case LOADMORE:
                page++;
                String format = String.format(urlList, page);
                OkHttpUtils.get().url(format).build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("teste", "onError: TweetUrl is error");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!TextUtils.isEmpty(response))
                            new AsyncTask<String, Void, List<TweetListBean>>() {
                                @Override
                                protected List<TweetListBean> doInBackground(String... params) {
                                    List<TweetListBean> tem = JSONUtils.parse2TweetList(params[0]);
                                    return tem;
                                }

                                @Override
                                protected void onPostExecute(List<TweetListBean> tweetListBeen) {
                                    if (tweetListBeen != null && tweetListBeen.size() > 0 && adapter != null) {
                                        listBean.addAll(tweetListBeen);
                                        adapter.notifyDataSetChanged();
                                        Toast t = new Toast(getActivity());
                                        refreshLayout.setRefreshing(false);
                                        Toast.makeText(getActivity(), "加载成功", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }.execute(response);
                    }
                });
                break;
        }

    }

    private void initView(View mview) {
        refreshLayout = (SwipyRefreshLayout) mview.findViewById(R.id.frag_tweet_base_refresh);
        listView = (ListView) mview.findViewById(R.id.frag_tweet_base_lv);

        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        //刷新操作
        refreshLayout.setRefreshing(true);
        if (direction == SwipyRefreshLayoutDirection.TOP) {
            getDataFromNet(REFRESH);
        } else if (direction == SwipyRefreshLayoutDirection.BOTTOM) {
            if (!isHot) {
                getDataFromNet(LOADMORE);
            }else {
                Toast.makeText(getActivity(), "已加载全部", Toast.LENGTH_SHORT).show();
                refreshLayout.setRefreshing(false);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (listBean != null && listBean.size() > 0)
            outState.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) listBean);
    }
}
