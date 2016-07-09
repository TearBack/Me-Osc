package com.test.zjj.m_osc.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.test.zjj.m_osc.R;
import com.test.zjj.m_osc.bean.TweetListBean;
import com.test.zjj.m_osc.utils.TimeUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class TweetListAdapter extends BaseAdapter {
    private List<TweetListBean> listBeen;
    private Context context;

    public TweetListAdapter(List<TweetListBean> listBeen,Context context) {
        this.listBeen = listBeen;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return listBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tweet_list, null);
            vh.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            vh.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            vh.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            vh.tv_zan = (TextView) convertView.findViewById(R.id.tv_zan);
            vh.sv_icon = (SimpleDraweeView) convertView.findViewById(R.id.iv_icon);
            vh.tv_from = (TextView) convertView.findViewById(R.id.tv_from);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        TweetListBean bean = listBeen.get(position);
        vh.tv_name.setText(bean.getAuthor());
        vh.tv_content.setText(bean.getBody());

        long time = TimeUtils.getInstance("yyyy-MM-dd HH:mm:ss").parseStr2Time(bean.getPubDate());
        String strTime = TimeUtils.getInstance().getTimeCompareToNow(time);
        vh.tv_time.setText(strTime);

        vh.tv_zan.setText(bean.getCommentCount() + "");

        String iconUrl = bean.getPortrait();
        if (!TextUtils.isEmpty(iconUrl))
            vh.sv_icon.setImageURI(iconUrl);

        if (position/3!=0){
            vh.tv_from.setVisibility(View.GONE);
        }
        return convertView;
    }

    class ViewHolder {
        private TextView tv_name, tv_content, tv_time, tv_from, tv_zan;
        private SimpleDraweeView sv_icon;
    }
}
