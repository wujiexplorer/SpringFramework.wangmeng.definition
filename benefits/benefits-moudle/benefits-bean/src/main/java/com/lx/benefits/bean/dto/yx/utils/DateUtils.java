package com.lx.benefits.bean.dto.yx.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 工具类：[日期]、[字符串]、[时间]三者的转换工具类
 * 三者的转换是非常常用的，所以定义此工具类。三者的简要说明如下：
 * [日期]：日期对象
 * [字符串]：指的是日期的字符串表示
 * [时间]：long型的值
 */
public class DateUtils {
    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static final String DATETIMEFORMAT = "yyyyMMddHHmmss";

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final long TIME_OF_MINUTE = 60000L;

    public static final long TIME_OF_HOUR = 3600000L;

    public static final long TIME_OF_DAY = 86400000L;

    /**
     * 将[日期]按照给定的[日期格式]转成[字符串]
     *
     * @param date   日期
     * @param format 日期格式
     * @return
     */
    public static String parseDateToString(Date date, String format) {
        if (date == null) {
            return null;
        }
        if (StringUtils.isEmpty(format)) {
            format = DATE_TIME_FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 将[时间]按照指定的[日期格式]转成[字符串]
     *
     * @param time   时间
     * @param format 日期格式
     * @return
     */
    public static String parseLongToString(long time, String format) {
        SimpleDateFormat mydate = new SimpleDateFormat(format);
        return mydate.format(new Date(time));
    }

    /**
     * 将[字符串]按照指定的[日期格式]转成[时间]
     *
     * @param time   字符串
     * @param format 日期格式
     * @return
     */
    public static long parseStringToLong(String time, String format) {
        SimpleDateFormat mydate = new SimpleDateFormat(format);
        try {
            Date date = mydate.parse(time);
            if (date != null) {
                return date.getTime();
            }
        } catch (ParseException e) {
            logger.error("Date parse error:", e);
        }
        return 0;
    }

    /**
     * 将[字符串]按照指定的[日期格式]转成[日期]
     *
     * @param time   字符串
     * @param format 日期格式
     * @return
     */
    public static Date parseStringToDate(String time, String format) {
        SimpleDateFormat mydate = new SimpleDateFormat(format);
        try {
            Date date = mydate.parse(time);
            if (date != null) {
                return date;
            }
        } catch (ParseException e) {
            logger.error("Date parse error:", e);
        }
        return null;
    }

    /**
     * 添加天数
     *
     * @param date   日期
     * @param amount 增加天数
     * @return
     */
    public static Date addDay(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.DAY_OF_MONTH, amount);

        return calendar.getTime();
    }

    /**
     * 获取某天的开始时间
     *
     * @param date 日期
     */
    public static Date getStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取某天的结束时间
     *
     * @param date 日期
     */
    public static Date getEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }
}
