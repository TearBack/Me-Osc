package com.test.zjj.m_osc.ui.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.test.zjj.m_osc.R;

import java.util.List;

/**
 * Created by Administrator on 2016/6/17.
 */
public class AdView extends RelativeLayout {
    private Context context;
    private LinearLayout ll_point;
    private TextView textView;
    private ViewPager viewPager;
    private AdviewListener listener;
    private List<SimpleDraweeView> svs;
    private List<String> titles;
    private int pointNum;
    private int currentIndex = 0;

    public AdView(Context context, AdviewListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;

        init();
    }
    public AdView(Context context, AttributeSet attrs, AdviewListener listener) {
        super(context, attrs);
        this.context = context;
        this.listener = listener;
        init();
    }

    public void initData() {
        if (listener != null) {
            //得到图片集合
            svs = listener.setImages();
            //得到标题集合标题
            titles = listener.setTitles();
            //得到圆点数量
            pointNum = svs.size();
            //设置适配器
            AdViewAdapter adapter = new AdViewAdapter();
            viewPager.setAdapter(adapter);
            //初始化圆点
                //添加圆点，并所有圆点白色
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,0,5,0);

            for (int i = 0; i < pointNum; i++) {
                ImageView image = new ImageView(context);
                image.setImageResource(R.drawable.selector);
                image.setLayoutParams(params);
                image.setEnabled(true);
                ll_point.addView(image);
            }
                //第一个圆点设置为红色
            ll_point.getChildAt(currentIndex).setEnabled(false);
            textView.setText(titles.get(currentIndex));
            //test
            Log.i("test", "-->initData: svs:" + svs.size() + ",titles:" + titles + ",pointnum:" + pointNum);
            //监听viewpager滑动
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    //滑动
                }

                @Override
                public void onPageSelected(int position) {
                    //选择,滑动改变标题
                    textView.setText(titles.get(position));
                    //改变圆点
                    ll_point.getChildAt(currentIndex).setEnabled(true); //原来的设为白色
                    ll_point.getChildAt(position).setEnabled(false);    //当前的设为红色
                    currentIndex = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    //状态改变
                }
            });
        }
    }



    private void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.headview_adloop, this);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        textView = (TextView) view.findViewById(R.id.tv_title);
        ll_point = (LinearLayout) view.findViewById(R.id.ll_point);
        initData();
    }

    public interface AdviewListener {
        List<SimpleDraweeView> setImages();
        List<String> setTitles();
    }

    class AdViewAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return svs.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(svs.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(svs.get(position));
            return svs.get(position);
        }
    }

}
