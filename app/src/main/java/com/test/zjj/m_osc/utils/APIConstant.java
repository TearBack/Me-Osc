package com.test.zjj.m_osc.utils;

/**
 * Created by Administrator on 2016/7/4.
 */
public class APIConstant {
    public static final String NEWS_LIST = "https://www.oschina.net/action/openapi/news_list?access_token=4bd3828f-1aa3-4d87-9e5b-8ba7ecde3713&catalog=1&page=%d&pageSize=20&dataType=json";
    public static final String NEWS_DETAIL = "https://www.oschina.net/action/openapi/news_detail?id=%d&access_token=4bd3828f-1aa3-4d87-9e5b-8ba7ecde3713";
    public static final String NEWS_BLOGLIST = "https://www.oschina.net/action/openapi/blog_list?access_token=4bd3828f-1aa3-4d87-9e5b-8ba7ecde3713&page=%d&pageSize=20&dataType=json";
    public static final String NEWS_BLOGDETAIL = "https://www.oschina.net/action/openapi/blog_detail?access_token=4bd3828f-1aa3-4d87-9e5b-8ba7ecde3713&dataType=json&id=%d";
    public static final String TWEET_LIST_HOT = "https://www.oschina.net/action/openapi/tweet_list?access_token=4bd3828f-1aa3-4d87-9e5b-8ba7ecde3713&page=%d&user=-1&pageSize=20&dataType=json";
    public static final String TWEET_LIST_NEW = "https://www.oschina.net/action/openapi/tweet_list?access_token=4bd3828f-1aa3-4d87-9e5b-8ba7ecde3713&page=%d&user=0&pageSize=20&dataType=json";
    public static final String TWEET_LIST_USER = "https://www.oschina.net/action/openapi/tweet_list?access_token=4bd3828f-1aa3-4d87-9e5b-8ba7ecde3713&page=%d&user=%d&pageSize=20&dataType=json";
    public static final String TWEET_LIST_DETAIL = "https://www.oschina.net/action/openapi/tweet_detail?access_token=4bd3828f-1aa3-4d87-9e5b-8ba7ecde3713&id=%d&dataType=json";
    public static final String REQUEST_ICON = "https://www.oschina.net/action/openapi/user_information?access_token=4bd3828f-1aa3-4d87-9e5b-8ba7ecde3713&dataType=json&user=2836650&friend=%d";
    public static final int FRAG_ZX = 0;
    public static final int FRAG_BLOG = 1;

}
