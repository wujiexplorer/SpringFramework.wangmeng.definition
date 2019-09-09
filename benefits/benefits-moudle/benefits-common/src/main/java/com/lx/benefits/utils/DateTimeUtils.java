package com.lx.benefits.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * @author LiuGaoJian
 * @version 1.0 2018/4/19
 */
@Slf4j
public class DateTimeUtils {

    public static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private DateTimeUtils() {
    }

    public static String timeToString(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static Date stringToTime(String dateTime, String format) {
        if (StringUtils.isBlank(dateTime)) {
            return null;
        }
        try {

            return new SimpleDateFormat(format).parse(dateTime);
        } catch (ParseException e) {
            log.error("字符串转化时间出错", e);
        }
        return null;
    }

    /**
     * 时间，转换格式
     *
     * @param origin
     * @param format
     * @return
     */
    public static Date convertFormat(Date origin, String format) {
        if (origin == null) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(dateFormat.format(origin));
        } catch (Exception e) {
            log.error("时间转换格式出错", e);
        }
        return null;
    }

    public static int getDayOfWeek() {
        Calendar now = Calendar.getInstance();
        int dayOfWeek = now.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return 7;
        }

        return dayOfWeek - 1;
    }

    /**
     * 增加、减少指定天数
     *
     * @param date
     * @param day
     * @return
     */
    public static Date addDay(final Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, day);
        return c.getTime();
    }

    /**
     * 增加、减少指定小时
     *
     * @param date
     * @param hour 要增加的小时（减少则为 负数）
     * @return
     */
    public static Date addHour(final Date date, int hour) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, hour);
        return c.getTime();
    }

    /**
     * 增加、减少指定分钟
     *
     * @param date
     * @param
     * @return
     */
    public static Date addMins(final Date date, int mins) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, mins);
        return c.getTime();
    }

    /**
     * 获取小时
     *
     * @param date
     * @return
     */
    public static int getHours(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR);
    }

    /**
     * 获取小时(24小时制)
     * @param date
     * @return
     */
    public static int getHourOfDay(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static String getLastMonthFirstDay(String fomat){
        SimpleDateFormat format=new SimpleDateFormat(fomat);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String date = format.format(c.getTime());
        return date;
    }


    public static void main(String args[]) {
        System.out.println(getLastMonthFirstDay("yyyy-MM-dd 00:00:00"));

        Date date1 = stringToTime("2019-07-12 10:00:00",DEFAULT_TIME_FORMAT);
        Date date2 = stringToTime("2019-07-12 09:40:16",DEFAULT_TIME_FORMAT);
        if((date1.getTime() - date2.getTime())/1000 > 15*60){
            System.out.println("ok!");
        }else {
            System.out.println("bad!");
        }
    }

    /**
     * 获取与当前时间差的秒数
     * @param date
     * @return
     */
    public static long getDifferentSeconds(final Date date){
        return (date.getTime() - System.currentTimeMillis())/1000;
    }

    /**
     * 去掉末尾的点和0
     * @param s
     * @return
     */
    public static String rvZeroAndDot(String s){
        if (s.isEmpty()) {
            return null;
        }
        if (s.indexOf(".0") > 0) {
            s = s.replace(".0", "");//去掉多余的0
        }
        return s;
    }

    /**
     * 获取一天的零点
     * @param date
     * @return
     */
    public static Date getZeroDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static String dateToString(Date date){
        if (date == null){
            return "";
        }
        DateFormat dateFormat = new SimpleDateFormat(DEFAULT_TIME_FORMAT);


        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            log.error("时间转换格式出错", e);
            return "";
        }
    }
    //获取当前时间
    public static Date getDate() {
    	LocalDate localDate = LocalDate.now();
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
		Date date = Date.from(instant);
    	return date;
    }
}
