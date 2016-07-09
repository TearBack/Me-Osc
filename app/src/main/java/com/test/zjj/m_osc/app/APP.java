package com.test.zjj.m_osc.app;

import android.app.Application;

/**
 * Created by Administrator on 2016/7/8.
 */
public class APP extends Application {
    public static UserInfo user;
    @Override
    public void onCreate() {
        super.onCreate();
        user = new UserInfo("wWwwWw_4914",2836650);
    }

    public class UserInfo{
        public String name;
        public int id;

        public UserInfo(String name, int id) {
            this.name = name;
            this.id = id;
        }
    }
}
