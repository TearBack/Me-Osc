package com.test.zjj.m_osc.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.test.zjj.m_osc.R;
import com.test.zjj.m_osc.ui.MainTab;

public class MainActivity extends AppCompatActivity {

    //    private Class[] mFragments = new Class[]{NewsFragment.class,TweetFragment.class,DiscoveryFragment.class,MeFragment.class};
    //    private String[] mTabText = new String[]{"综合", "动弹","快速", "发现", "我的"};
    //    private int[] mTabImgs = new int[]{R.drawable.selector_ic_nav_news, R.drawable.selector_ic_nav_tweet,null, R.drawable.selector_ic_nav_discovery,
    //            R.drawable.selector_ic_nav_me};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        Log.i("testi", "LineNum:30-->MainActivity-->onCreate: network:");

        initView();
    }

    private void initView() {
        initTabs();
    }

    private void initTabs() {
        FragmentTabHost tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);  //初始化
        MainTab[] mainTab = MainTab.values();
        for (int i = 0; i < mainTab.length; i++) {
            MainTab tab = mainTab[i];
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(getString(tab.getResName()));

            View indicator = LayoutInflater.from(this).inflate(R.layout.tabbutton, null);
            ImageView icon = (ImageView) indicator.findViewById(R.id.tc_iv);
            TextView title = (TextView) indicator.findViewById(R.id.tc_tv);
            icon.setImageResource(tab.getResIcon());
            title.setText(getString(tab.getResName()));

            if (i == 2){
                indicator.setVisibility(View.INVISIBLE);
                ((ImageView) findViewById(R.id.main_iv_quick)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "我是+", Toast.LENGTH_SHORT).show();
                    }
                });}

            tabSpec.setIndicator(indicator);

            tabHost.addTab(tabSpec, tab.getClz(), null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                Toast.makeText(MainActivity.this, "搜索", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
