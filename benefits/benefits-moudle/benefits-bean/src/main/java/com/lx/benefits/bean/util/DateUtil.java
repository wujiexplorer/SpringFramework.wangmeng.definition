package com.lx.benefits.bean.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DateUtil {

    private final static Logger logger = LoggerFactory.getLogger(DateUtil.class);
    
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_NOW = "yyyyMMddHHmmss";
    public static final String DATE_DAY_MONTH = "MM-dd";

    public static Date now() {
        return new Date();
    }

    public static long getNowTimestamp13() {
        return now().getTime();
    }

    public static long getNowTimestamp10() {
        return now().getTime() / 1000;
    }

    public static long calTimeUntilNow(Long start) {
        return getNowTimestamp10() - start;
    }

    /**
     * 获取当前时间的时间戳，精确到毫秒
     * @return 返回当前时间的时间戳
     */
    public static Long getTimestamp()
    {
        return System.currentTimeMillis();
    }

    public static final String timestamp10ToStringyyyyMMdd(Long time) {
        return new SimpleDateFormat("yyyyMMdd").format(new Date(time * 1000));
    }

    public static final String timestamp10ToStringyyyyMMdd2(Long time) {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(time * 1000));
    }

    /**
     * Unix时间戳转换为指定类型
     * @param time
     * @return
     */
    public static String unixTime2Date(Long time) {
        if (null == time) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
        String dateStr = sdf.format(new Date(time*1000));
        return dateStr;
    }

    /**
     * Unix时间戳转换为指定类型
     * @param time
     * @return
     */
    public static String unixTime2Date(Integer time) {
        if (null == time) {
            return null;
        }
        long longTime = time.longValue();
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
        return sdf.format(new Date(longTime* 1000));
    }

    public static final String timetamp10ToStringMMdd(Long time) {
        return new SimpleDateFormat("MM.dd").format(new Date(time * 1000));
    }

    public static final String timetamp10ToStringMMDDHHmm(Long time) {
        return new SimpleDateFormat("MM-dd HH:mm").format(new Date(time * 1000));
    }

    public static final String getNowStr() {
        return dateToString(now(), "yyyy-MM-dd");
    }

    public static final String getNowTimeStr() {
        return dateToString(now(), DATETIME_FORMAT);
    }

    public static final String getMillisecondStr() {
        return dateToString(now(), "yyyy-MM-dd HH:mm:ss SSS");
    }
    
    public static final String preNowXDay(int day) {
        Calendar c = Calendar.getInstance(Locale.getDefault());
        c.add(Calendar.DAY_OF_MONTH, -day);
        return dateToString(c.getTime(), "yyyy-MM-dd");
    }

    public static final String dateToString(Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static final Long getTimestamp10(String dateStr, String format) {
        if (StringUtils.isBlank(format)) {
            return null;
        }
        Date date = stringToDate(dateStr, format);
        return date.getTime() / 1000;
    }

    public static final Date stringToDate(String source, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            return null;
        }
    }

    public static int getNowHour() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static final Long getStartTimeOfDay10(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        getStartTimeOfDay(calendar);
        return calendar.getTimeInMillis() / 1000;
    }

    public static final int getDateHour(Long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time * 1000);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static final Date getStartTimeOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static List<String> weekAbscissa(Integer date, Integer vetor) {
        List<String> weeks = new ArrayList<>();
        Long time = DateUtil.getStartTimeOfDay10(new Date());
        for (int i = 0; i < date; i++) {
            Long transfer = time - i * vetor;
            String day = DateUtil.timetamp10ToStringMMdd(transfer);
            weeks.add(i, day);
        }
        Collections.reverse(weeks);
        return weeks;
    }

    public static Long getTimeWithoutSS(Long time) {
        String date = unixTime2Date(time);
        Date date1 = stringToDate(date, DATETIME_FORMAT);
        System.out.println(date1);
        return date1.getTime() / 1000;
    }

    public static Long getNowFromZero() {
        Long now = getNowTimestamp10();
        return getStartTimeOfDay10(new Date()) + 86400 - now;
    }

    /**
     * 日期转unixTime
     * @param date     2015-01-15 11:48:13   默认 yyyy-MM-dd HH:mm:ss 格式
     * @return Long
     */
    public static Long date2LongUnixTime(String date) {
        if (null == date || date.isEmpty()) {
            return null;
        }
        int len = date.length();
        SimpleDateFormat sdf = new SimpleDateFormat(len >= 15 ? DATETIME_FORMAT: DATE_FORMAT);
        Long unixTime = 0L;
        try {
            unixTime = sdf.parse(date).getTime() / 1000;
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        return unixTime;
    }


    /**
     * 日期转unixTime
     * @param date     2015-01-15 11:48:13   默认 yyyy-MM-dd HH:mm:ss 格式
     * @return Integer
     */
    public static Integer date2IntegerUnixTime(String date) {
        if (null == date || date.isEmpty()) {
            return null;
        }
        int len = date.length();
        SimpleDateFormat sdf = new SimpleDateFormat(len >= 15 ? DATETIME_FORMAT: DATE_FORMAT);
        Long unixTime = 0L;
        try {
            unixTime = sdf.parse(date).getTime() / 1000;
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        return unixTime.intValue();
    }


    /**
     * 日期转unixTime
     * @param date     2015-01-15 11:48:13   默认 yyyy-MM-dd HH:mm:ss 格式
     * @return Long
     */
    public static Timestamp date2Timestamp(String date) {
        if (null == date || date.isEmpty()) {
            return null;
        }
        int len = date.length();
        SimpleDateFormat sdf = new SimpleDateFormat(len >= 15 ? DATETIME_FORMAT: DATE_FORMAT);
        Timestamp timestamp = null;
        
        try {
            Long unixTime = sdf.parse(date).getTime();
            timestamp = new Timestamp(unixTime);
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        return timestamp;
    }

    /**
     * Unix时间戳转换为指定类型
     * @param time
     * @return
     */
    public static Timestamp unixTime2Timestamp(Long time) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
        Timestamp timestamp = null;
        try {
            timestamp = new Timestamp(time * 1000);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return timestamp;
    }

    /**
     * Unix时间戳转换为指定类型
     * @param time
     * @return
     */
    public static Timestamp unixTime2Timestamp(Integer time) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
        Timestamp timestamp = null;
        try {
            timestamp = new Timestamp(time * 1000);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return timestamp;
    }


    public static final String date2String(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
        return sdf.format(date);
    }

    /**
     * 获取当前Date类型时间
     * @param format
     * @return
     */
    public static String getCurrentDate(String format) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateStr = sdf.format(calendar.getTime());
        return dateStr;
    }

    /**
     * 日期转unixTime
     * @param date     2015-01-15 11:48:13
     * @param format   DATETIME_FORMAT
     * @return
     */
    public static Long date2UnixTime(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Long unixTime = 0L;
        try {
            unixTime = sdf.parse(date).getTime() / 1000;
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        return unixTime;
    }

    /**
     * Unix时间戳转换为指定类型
     * @param time
     * @param format
     * @return
     */
    public static String unixTime2Date(Long time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time*1000));
    }

    public static boolean isThisTime(long time, String pattern) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        //参数时间
        String param = sdf.format(date);
        //当前时间
        String now = sdf.format(new Date());
        if (param.equals(now)) {
            return true;
        }
        return false;
    }

   public static boolean check (String str) {
        SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
        try {
            sd.setLenient(false);
            sd.parse(str);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isThisTime(String dateStr, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            //参数时间
            Date date = sdf.parse(dateStr);
            SimpleDateFormat sd =new SimpleDateFormat("MM-dd");

            String param =  sd.format(date);
            //当前时间
            String now = sd.format(new Date());
            if (param.equals(now)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isToday(long time) {
        return isThisTime(time, "yyyy-MM-dd");
    }


    /**
     * 获取Unix时间戳
     * @return
     */
    public static Long getCurrentUnixTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 将字符串解析成日期类型，格式自定
     * @param dateStr 待解析的字符串
     * @return 返回解析后的日期
     */
    public static Date getDate(String dateStr, String formatStr)
    {
        Date date = null;
        try
        {
            if (dateStr != null && !"".equals(formatStr)&&!"".equals(dateStr))
            {
                DateFormat dateFormat = new SimpleDateFormat(formatStr);
                date = dateFormat.parse(dateStr);
            }
        }
        catch(ParseException parse)
        {
            //log.error("getDate()方法解析日期出错！", parse);
        }
        return date;
    }

    /**
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isInRange(Date startTime,Date endTime) {

        Long currentTime = System.currentTimeMillis();
        Long start =  startTime.getTime();
        Long end =  endTime.getTime();
        boolean isInRange = true;
        if (null != startTime && currentTime < start) {
            isInRange = false;
        }
        if (null != endTime && currentTime > end) {
            isInRange = false;
        }
        return isInRange;
    }

    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 返回日期的月份，1-12,即yyyy-MM-dd中的MM
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回日期的年,即yyyy-MM-dd中的yyyy
     *
     * @param date
     *            Date
     * @return int
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


    public static int calDiffMonth(String startDate,String endDate) {
        int result = 0;
        try {
            SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
            Date start = sfd.parse(startDate);
            Date end = sfd.parse(endDate);
            int startYear = getYear(start);
            int startMonth = getMonth(start);
            int startDay = getDay(start);
            int endYear = getYear(end);
            int endMonth = getMonth(end);
            int endDay = getDay(end);
            if (startDay > endDay) { //1月17  大于 2月28
                if (endDay == getDaysOfMonth(getYear(new Date()), 2)) {   //也满足一月
                    result = (endYear - startYear) * 12 + endMonth - startMonth;
                } else {
                    result = (endYear - startYear) * 12 + endMonth - startMonth - 1;
                }
            } else {
                result = (endYear - startYear) * 12 + endMonth - startMonth;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static int calDiffMonth(Integer leaveTime) {
        // 当前时间
        Long currentTime = getCurrentUnixTime();
        String currentDate = unixTime2Date(currentTime,DATE_FORMAT);
        // 离职日期
        String leaveDate = unixTime2Date(leaveTime.longValue(),DATE_FORMAT);
        return  calDiffMonth(leaveDate, currentDate);
    }


    public static Date stepMonth(Date sourceDate, int month) {

        Calendar c = Calendar.getInstance();
        c.setTime(sourceDate);
        c.add(Calendar.MONTH, month);

        return c.getTime();
     }


    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param time
     * @return
     */
    public static int differentDays(Integer time)
    {
        // 当前时间
        Long currentTime = getCurrentUnixTime();

        int days = (int) ((time - currentTime) / (1000*3600*24));
        return days;
    }

     /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysBySecond(Date date1,Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }


    public static boolean isRecoveryTime(Integer leaveTime) {
        // 当前时间
        Long currentTime = getCurrentUnixTime();
        String currentDateStr = unixTime2Date(currentTime,DATE_FORMAT);
        // 离职日期
        String leaveDateStr = unixTime2Date(leaveTime.longValue(),DATE_FORMAT);
        int month = calDiffMonth(leaveDateStr, currentDateStr);
        if (month >= 2) {
            Date leaveDate = getDate(leaveDateStr,DATE_FORMAT);
            Date currentDate = getDate(currentDateStr,DATE_FORMAT);
            Date date = stepMonth(leaveDate,2);
            int day = differentDaysBySecond(date,currentDate);
            if (day >= 1) {
                return  true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws  Exception{

        String time = "2017-03-12";
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");//yyyy-mm-dd, 会出现时间不对, 因为小写的mm是代表: 秒
        Date utilDate = sdf.parse(time);
        System.out.println(utilDate);
        System.out.println("1111");
        System.out.println(preNowXDay(10));
        System.out.println(DateUtil.stringToDate(DateUtil.preNowXDay(1),DateUtil.DATE_FORMAT));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String sDate = simpleDateFormat.format(new Date());
        Date date = null;
        try {
            date = simpleDateFormat.parse(sDate);
            System.out.println(date);
        } catch (ParseException e) {
            //log.debug("Get Current Date Exception [by ninja.hzw]"+e);
        }

    }


}
