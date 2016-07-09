package com.test.zjj.m_osc.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/7/5.
 */
public class TimeUtils {
    private static TimeUtils sTimeUtils;
    private static DateFormate sDateFormat;

    private TimeUtils(String template) {
        sDateFormat = new DateFormate(template);
    }

    private TimeUtils() {
        sDateFormat = new DateFormate();
    }

    public static DateFormate getInstance(String template) {
        if (sTimeUtils == null) {
            synchronized (TimeUtils.class) {
                if (sTimeUtils == null)
                    sTimeUtils = new TimeUtils(template);
            }
        }
        return sTimeUtils.sDateFormat;
    }

    public static DateFormate getInstance() {
        if (sTimeUtils == null) {
            synchronized (TimeUtils.class) {
                if (sTimeUtils == null)
                    sTimeUtils = new TimeUtils();
            }
        }
        return sTimeUtils.sDateFormat;
    }

    public class DateFormate extends SimpleDateFormat {
        public DateFormate(String template) {
            super(template);
        }

        public DateFormate() {
        }

        public long parseStr2Time(String date) {
            if (TextUtils.isEmpty(date)) return -1;
            long time = -1;
            try {
                time = this.parse(date).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return time;
        }

        public String getTimeCompareToNow(long time) {
            long now = System.currentTimeMillis();
            long todayStart = weeHours(new Date(now), 0);   //得到今天凌晨时间
            long re = todayStart - time;
            if (re < 0) {
                long dis = now - time;
                //时间为今天之内,n分钟前,n小时前
                if (dis < 60 * 60 * 1000) {
                    //一小时内，n分钟前
                    int minPre = (int) (dis / 60 / 1000);
                    return minPre + "分钟前";
                } else {
                    int houPre = (int) (dis / 60 / 60 / 1000);
                    return houPre + "小时前";
                }

            } else {
                //时间为昨天，前天，n天前
                int dayPre = (int) (re / (24 * 60 * 60 * 1000));
                if (dayPre == 0) {
                    return "昨天";
                } else if (dayPre == 1) {
                    return "前天";
                } else {
                    return dayPre + 1 + "天前";
                }
            }
        }

        /**
         * 凌晨
         *
         * @param date
         * @return
         * @flag 0 返回yyyy-MM-dd 00:00:00日期<br>
         * 1 返回yyyy-MM-dd 23:59:59日期
         */
        private long weeHours(Date date, int flag) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);
            int second = cal.get(Calendar.SECOND);
            //时分秒（毫秒数）
            long millisecond = hour * 60 * 60 * 1000 + minute * 60 * 1000 + second * 1000;
            //凌晨00:00:00
            cal.setTimeInMillis(cal.getTimeInMillis() - millisecond);

            if (flag == 0) {
                return cal.getTime().getTime();
            } else if (flag == 1) {
                //凌晨23:59:59
                cal.setTimeInMillis(cal.getTimeInMillis() + 23 * 60 * 60 * 1000 + 59 * 60 * 1000 + 59 * 1000);
            }
            return cal.getTime().getTime();
        }
    }
}
