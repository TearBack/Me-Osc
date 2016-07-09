package com.test.zjj.m_osc.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.test.zjj.m_osc.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        initToolBar();
        initWebView();
        String mUrl = getIntent().getStringExtra("mUrl");
        Log.i("testi", "LineNum:26-->WebViewActivity-->onCreate: mUl:" + mUrl);
        if (!TextUtils.isEmpty(mUrl))
            webView.loadUrl(mUrl);
    }

    private void initWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    private void initToolBar() {
        toolbar.setTitle("网页");
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @BindView(R.id.wb_toolbar)
    public Toolbar toolbar;
    @BindView(R.id.webview)
    public WebView webView;
}
