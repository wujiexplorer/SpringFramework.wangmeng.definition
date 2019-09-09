package com.lx.benefits.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by moxuandeng on 2017/7/29 14:25.
 */
public class LocalDateTimeUtil {
    // 线程安全的
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter UTC_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public static final DateTimeFormatter MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyyMM");

    private static final DateTimeFormatter STD_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter SLASH_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    private static final DateTimeFormatter SIMPLE_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static final DateTimeFormatter SIMPLE_DAY_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * 没有年份的时间格式
     */
    public static final DateTimeFormatter NO_YEAR_DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd HH:mm:ss");

    private LocalDateTimeUtil() {
    }

    public static LocalDateTime string2LocalDateTime(String time) {
        if (time == null) {
            return null;
        }
        if (time.contains(".")) {
            time = time.split("\\.")[0];
        }
        return LocalDateTime.parse(time, DATE_TIME_FORMATTER);
    }

    public static LocalDateTime utcString2LocalDateTime(String time) {
        if (time == null) {
            return null;
        }
        if (time.contains(".")) {
            time = time.split("\\.")[0];
        }
        return LocalDateTime.parse(time, UTC_DATE_TIME_FORMATTER);
    }


    /**
     * 将DATE转为LocalDateTime
     *
     * @param date 日期
     */
    public static LocalDateTime trans2LocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(date.toInstant(), zone);
    }

    /**
     * 将DATE转为LocalDate
     *
     * @param date 日期
     */
    public static LocalDate trans2LocalDate(Date date) {
        if (date == null) {
            return null;
        }
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(date.toInstant(), zone).toLocalDate();
    }

    /**
     * 将LocalDateTime 转为 Date
     */
    public static Date trans2Date(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        ZoneId zone = ZoneId.systemDefault();
        return Date.from(localDateTime.atZone(zone).toInstant());
    }

    /**
     * 将LocalDate转为yyyyMM格式
     *
     * @param date 时间
     */
    public static String toMonthString(LocalDate date) {
        if (date == null) {
            return "";
        }
        return date.format(MONTH_FORMATTER);
    }

    /**
     * 将LocalDate转为yyyyMM格式
     *
     * @param date 时间
     */

    public static Integer toMonthInteger(LocalDate date) {
        if (date == null) {
            return 0;
        }
        return Integer.valueOf(toMonthString(date));
    }


    /**
     * 将LocalDate转为yyyy/MM/dd格式
     *
     * @param date 时间
     */
    public static String toSlashDateString(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.format(SLASH_DATE_FORMATTER);
    }

    /**
     * yyyy/MM/dd格式字符串转为日期
     *
     * @param timeStr 时间字符串
     * @return 日期
     */
    public static LocalDate slashDateString2Date(String timeStr) {
        if (StringUtils.isBlank(timeStr)) {
            return null;
        }
        return LocalDate.parse(timeStr, SLASH_DATE_FORMATTER);
    }

    /**
     * 将LocalDateTime转为yyyy/MM/dd格式
     */
    public static String toSlashDateString(LocalDateTime time) {
        if (time == null) {
            return null;
        }
        return toSlashDateString(time.toLocalDate());
    }

    public static String LocalDate2String(LocalDate date, DateTimeFormatter formatter)
    {
        if (date == null)
        {
            return null;
        }
        return date.format(formatter);
    }

    /**
     * 将LocalDate转为yyyy-MM-dd格式
     *
     * @param date 时间
     */
    public static String toStdDateString(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.format(STD_DATE_FORMATTER);
    }


    /**
     * yyyy-MM-dd格式字符串转为日期
     *
     * @param timeStr 时间字符串
     * @return 日期
     */
    public static LocalDate stdDateString2Date(String timeStr) {
        if (StringUtils.isBlank(timeStr)) {
            return null;
        }
        return LocalDate.parse(timeStr, STD_DATE_FORMATTER);
    }

    public static String toStdDateString(LocalDateTime time) {
        if (time == null) {
            return null;
        }
        return toStdDateString(time.toLocalDate());
    }

    /**
     * LocalDateTime 转为标准格式时间
     *
     * @param date 时间
     */
    public static String toString(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        return date.format(DATE_TIME_FORMATTER);
    }

    /**
     * Date 转为标准格式时间
     *
     * @param date 时间
     */
    public static String toString(Date date) {
        if (date == null) {
            return "";
        }
        return toString(trans2LocalDateTime(date));
    }

    public static String toSimpleString(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        return date.format(SIMPLE_DATE_FORMATTER);
    }


    public static Date simpleStr2Date(String time) {
        if (StringUtils.isBlank(time)) {
            return null;
        }
        return trans2Date(LocalDateTime.parse(time, SIMPLE_DATE_FORMATTER));
    }

    public static Date utcToDate(String time) {
        if (StringUtils.isBlank(time)) {
            return null;
        }
        return trans2Date(utcString2LocalDateTime(time));
    }


    public static Date timeStampToDate(String timeStamp) {
        if (StringUtils.isBlank(timeStamp)) {
            return null;
        }
        Instant instant = Instant.ofEpochMilli(Long.valueOf(timeStamp));
        return trans2Date(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()));
    }
}
