package com.test.zjj.m_osc.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.test.zjj.m_osc.bean.NewsList;
import com.test.zjj.m_osc.bean.NewsZxDetailBean;
import com.test.zjj.m_osc.bean.TweetListBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/4.
 */
public class JSONUtils {
    public static List<NewsList> parse2newslist(String json, int frag) {
        if (json == null) return null;
        List<NewsList> list = null;
        try {
            JSONObject obj = new JSONObject(json);
            String jsonArr = null;

            if (frag == APIConstant.FRAG_ZX) {
                jsonArr = obj.getJSONArray("newslist").toString();
            } else if (frag == APIConstant.FRAG_BLOG) {
                jsonArr = obj.getJSONArray("bloglist").toString();
            }

            if (!TextUtils.isEmpty(jsonArr))
                list = JSON.parseArray(jsonArr, NewsList.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<TweetListBean> parse2TweetList(String json) {
        if (TextUtils.isEmpty(json)) return null;
        List<TweetListBean> listBean = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray arry = obj.getJSONArray("tweetlist");
            for (int i = 0; i < arry.length(); i++) {
                JSONObject object = arry.getJSONObject(i);
                TweetListBean bean = new TweetListBean();

                bean.setAuthor(object.optString("author"));
                bean.setAuthorid(object.optInt("authorid"));
                bean.setBody(object.optString("body"));
                bean.setCommentCount(object.optInt("commentCount"));
                bean.setId(object.optInt("id"));
                bean.setPortrait(object.optString("portrait"));
                bean.setPubDate(object.optString("pubDate"));

                String imgBig = object.optString("imgBig");
                if (imgBig != null)
                    bean.setImgBig(imgBig);

                String imgSmall = object.optString("imgSmall");
                if (imgSmall != null)
                    bean.setImgSmall(imgSmall);

                listBean.add(bean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listBean;
    }

    public static NewsZxDetailBean parse2NewsZxDetail(String json){
        if (TextUtils.isEmpty(json)) return null;
        NewsZxDetailBean newsZxDetailBean = new Gson().fromJson(json, NewsZxDetailBean.class);
        return newsZxDetailBean;
    }
}
