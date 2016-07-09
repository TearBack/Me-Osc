package com.test.zjj.m_osc.ui.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.test.zjj.m_osc.R;
import com.test.zjj.m_osc.bean.NewsZxDetailBean;
import com.test.zjj.m_osc.utils.APIConstant;
import com.test.zjj.m_osc.utils.JSONUtils;
import com.test.zjj.m_osc.utils.Network;
import com.test.zjj.m_osc.utils.TimeUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class ZxDetail extends Activity {

    private String url = APIConstant.NEWS_DETAIL;
    private NewsZxDetailBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zx_detail);
        ButterKnife.bind(this);
        tvs = new TextView[]{tv_url_1,tv_url_2,tv_url_3,tv_url_4,tv_url_5,tv_url_6,tv_url_7,tv_url_8,tv_url_9,tv_url_10};
        initToolbar();
        initWebView();


        int zxid = getIntent().getIntExtra("zxid", 0);
        if (zxid != 0) {
            url = String.format(url, zxid);
            getDataFromNet(url);
        }


    }

    private void initWebView() {
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setTextZoom(100);     //文字大小，默认100
    }

//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what == 0x101) {
//                CharSequence cs = (CharSequence) msg.obj;
//                tv_body.setText(cs);
//            }
//        }
//    };
//    private void bindHtmlText(final String html) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Html.ImageGetter imgGetter = new Html.ImageGetter() {
//                    public Drawable getDrawable(String source) {
//                        Drawable drawable = null;
//                        URL url;
//                        try {
//                            url = new URL(source);
//                            Log.i("RG", "url---?>>>" + url);
//                            drawable = Drawable.createFromStream(url.openStream(), ""); // 获取网路图片
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            return null;
//                        }
//                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
//                                drawable.getIntrinsicHeight());
//                        return drawable;
//                    }
//                };
//
//                CharSequence charSequence = Html.fromHtml(html, imgGetter, null);
//                Message msg = new Message();
//                msg.what = 0x101;
//                msg.obj = charSequence;
//                mHandler.sendMessage(msg);
//            }
//        }).start();
//    }

    private void getDataFromNet(String murl) {
        if (Network.isConnected(this)) {
            OkHttpUtils.get().url(murl).build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Log.e("teste", "onError: -------");
                }

                @Override
                public void onResponse(String response, int id) {
                    bean = JSONUtils.parse2NewsZxDetail(response);
                    if (bean != null) {
                        tv_title.setText(bean.getTitle());
                        tv_source.setText(bean.getAuthor());
                        long time = TimeUtils.getInstance("yyyy-MM-dd HH:mm:ss").parseStr2Time(bean.getPubDate());
                        String strTime = TimeUtils.getInstance().getTimeCompareToNow(time);
                        if (!TextUtils.isEmpty(strTime))
                            tv_time.setText(strTime);
                        //内容
//                        tv_body.setMovementMethod(LinkMovementMethod.getInstance());

//                        bindHtmlText(bean.getBody());

                        webView.loadData(bean.getBody(), "text/html; charset=UTF-8", null);

                        for (int i = 0; i < tvs.length; i++) {
                            NewsZxDetailBean.RelativiesBean rBean = bean.getRelativies().get(i);
                            SpannableString sp = new SpannableString(rBean.getTitle());
                            sp.setSpan(new URLSpan(rBean.getUrl()),0,rBean.getTitle().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            sp.setSpan(new ForegroundColorSpan(Color.BLUE),0,rBean.getTitle().length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            tvs[i].setMovementMethod(LinkMovementMethod.getInstance());
                            tvs[i].setText(sp);
                        }
                    }
                }
            });
        }
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.zx_toolbar);
        toolbar.setTitle("咨询详情");
        toolbar.setNavigationIcon(R.drawable.back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.inflateMenu(R.menu.zxdetail_activity_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.refresh:
                        Toast.makeText(ZxDetail.this, "refersh", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.textsize:
                        Toast.makeText(ZxDetail.this, "textsize", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    @BindView(R.id.tv_title)
    public TextView tv_title;
    @BindView(R.id.tv_source)
    public TextView tv_source;
    @BindView(R.id.tv_time)
    public TextView tv_time;
    //    @BindView(R.id.tv_body)
//    public TextView tv_body;
    @BindView(R.id.webview)
    public WebView webView;

    private TextView[] tvs ;

    @BindView(R.id.tv_url_1)
    public TextView tv_url_1;
    @BindView(R.id.tv_url_2)
    public TextView tv_url_2;
    @BindView(R.id.tv_url_3)
    public TextView tv_url_3;
    @BindView(R.id.tv_url_4)
    public TextView tv_url_4;
    @BindView(R.id.tv_url_5)
    public TextView tv_url_5;
    @BindView(R.id.tv_url_6)
    public TextView tv_url_6;
    @BindView(R.id.tv_url_7)
    public TextView tv_url_7;
    @BindView(R.id.tv_url_8)
    public TextView tv_url_8;
    @BindView(R.id.tv_url_9)
    public TextView tv_url_9;
    @BindView(R.id.tv_url_10)
    public TextView tv_url_10;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
