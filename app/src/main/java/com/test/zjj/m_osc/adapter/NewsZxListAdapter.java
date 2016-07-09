package com.test.zjj.m_osc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.zjj.m_osc.R;
import com.test.zjj.m_osc.bean.NewsList;
import com.test.zjj.m_osc.utils.APIConstant;
import com.test.zjj.m_osc.utils.TimeUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/7/4.
 */
public class NewsZxListAdapter extends BaseAdapter {
    private List<NewsList> mList;
    private int flag;

    public NewsZxListAdapter(List<NewsList> list, int state) {
        mList = list;
        flag = state;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ZxViewHolder vh;
        if (convertView == null) {
            vh = new ZxViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_news, null);
            vh.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            vh.tv_des = (TextView) convertView.findViewById(R.id.tv_description);
            vh.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            vh.tv_count = (TextView) convertView.findViewById(R.id.tv_comment_count);
            vh.iv_today = (ImageView) convertView.findViewById(R.id.iv_today);
            convertView.setTag(vh);
        } else {
            vh = (ZxViewHolder) convertView.getTag();
        }
        NewsList entity = mList.get(position);
        vh.tv_title.setText(entity.getTitle());
        vh.tv_des.setText(entity.getTitle());
        vh.tv_count.setText(entity.getCommentCount() + "");

        long time = TimeUtils.getInstance("yyyy-MM-dd HH:mm:ss").parseStr2Time(entity.getPubDate());
        String strTime = TimeUtils.getInstance().getTimeCompareToNow(time);
//        Log.i("testi", "LineNum:64-->NewsZxListAdapter-->getView: strTime:" + strTime+",cont:"+strTime.contains("天"));
        if (flag == APIConstant.FRAG_ZX) {
            if (strTime.contains("天")) {
                vh.iv_today.setVisibility(View.GONE);
            } else {
                vh.iv_today.setVisibility(View.VISIBLE);
                vh.iv_today.setImageResource(R.drawable.ic_label_today);
            }
            vh.tv_time.setText(strTime);
        } else if (flag == APIConstant.FRAG_BLOG) {
            int type = entity.getType();
            if (type==1){
                //1原创   4转载
                vh.iv_today.setVisibility(View.VISIBLE);
                vh.iv_today.setImageResource(R.drawable.ic_label_originate);
            }else{
                vh.iv_today.setVisibility(View.GONE);
            }
            vh.tv_time.setText(entity.getAuthor()+"   "+strTime);
        }
        return convertView;
    }

    class ZxViewHolder {
        private TextView tv_title, tv_des, tv_time, tv_count;
        private ImageView iv_today;
    }
}
