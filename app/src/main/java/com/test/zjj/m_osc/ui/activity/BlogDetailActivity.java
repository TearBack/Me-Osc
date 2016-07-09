package com.test.zjj.m_osc.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.test.zjj.m_osc.R;
import com.test.zjj.m_osc.bean.NewsZxDetailBean;
import com.test.zjj.m_osc.utils.APIConstant;
import com.test.zjj.m_osc.utils.JSONUtils;
import com.test.zjj.m_osc.utils.Network;
import com.test.zjj.m_osc.utils.TimeUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class BlogDetailActivity extends Activity {

    private String mUrl;
    private NewsZxDetailBean mBean;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_general_blog_detail);
        ButterKnife.bind(this);
        initToolbar();
        initWebView();

        int blid = getIntent().getIntExtra("blid", 0);
        type = getIntent().getIntExtra("type", 0);
        if (blid != 0)
            mUrl = String.format(APIConstant.NEWS_BLOGDETAIL, blid);
        getDataFromNet();

    }

    private void initWebView() {
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setTextZoom(100);     //文字大小，默认100

//        webView.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                Intent intent = new Intent(BlogDetailActivity.this,WebViewActivity.class);
//                intent.putExtra("mUrl",url);
//                return true;
//            }
//        });

    }

    private void getDataFromNet() {
        if (TextUtils.isEmpty(mUrl)) return;
        if (Network.isConnected(this)) {
            OkHttpUtils.get().url(mUrl).build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Log.e("teste", "onError: url is error");
                }

                @Override
                public void onResponse(String response, int id) {
                    if (!TextUtils.isEmpty(response)) {
                        mBean = JSONUtils.parse2NewsZxDetail(response);
                        tv_name.setText(mBean.getAuthor());

                        long time = TimeUtils.getInstance("yyyy-MM-dd HH:mm:ss").parseStr2Time(mBean.getPubDate());
                        String strTime = TimeUtils.getInstance().getTimeCompareToNow(time);
                        tv_pub_date.setText(strTime + " (" + mBean.getPubDate() + ")");

                        if (type == 4) {
                            iv_orign.setVisibility(View.GONE);
                        }

                        tv_title.setText(mBean.getTitle());
                        tv_info_comment.setText(mBean.getCommentCount() + "");
                        //内容
                        webView.loadData(mBean.getBody(),"text/html; charset=UTF-8",null);
                        //相关连接
                        for (int i = 0; i < 3; i++) {
                            View about = LayoutInflater.from(BlogDetailActivity.this).inflate(R.layout.lay_blog_detail_about, ll_about);
                            TextView abT = (TextView) about.findViewById(R.id.tv_title);
                            abT.setText(mBean.getRelativies().get(i).getTitle());
                            final int finalI = i;
                            about.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(BlogDetailActivity.this, WebViewActivity.class);
                                    intent.putExtra("mUrl", mBean.getRelativies().get(finalI).getUrl());
                                    startActivity(intent);
                                }
                            });
                        }
                        //头像
                        String format = String.format(APIConstant.REQUEST_ICON, mBean.getAuthorid());
                        OkHttpUtils.get().url(format).build().execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e("teste", "onError: Url is error");
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                if (!TextUtils.isEmpty(response))
                                    try {
                                        JSONObject obj = new JSONObject(response);
                                        String iconUrl = obj.optString("portrait");
                                        sv_icon.setImageURI(iconUrl);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                            }
                        });

                    }
                }
            });
        } else {
            //无网络
            Log.e("teste", "getDataFromNet: net  error");
        }
    }

    private void initToolbar() {
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setTitle("博客详情");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.inflateMenu(R.menu.menu_blogdetail);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_bl_more) {
                    Toast.makeText(BlogDetailActivity.this, "举报", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }


    @BindView(R.id.bl_toolbar)
    public Toolbar toolbar;
    @BindView(R.id.tv_name)
    public TextView tv_name;
    @BindView(R.id.tv_pub_date)
    public TextView tv_pub_date;
    @BindView(R.id.tv_title)
    public TextView tv_title;
    @BindView(R.id.tv_info_comment)
    public TextView tv_info_comment;
    @BindView(R.id.bl_detail_wb)
    public WebView webView;
    @BindView(R.id.lay_blog_detail_about)
    public LinearLayout ll_about;
    @BindView(R.id.iv_label_originate)
    public ImageView iv_orign;
    @BindView(R.id.bl_detail_sv)
    public SimpleDraweeView sv_icon;


}
