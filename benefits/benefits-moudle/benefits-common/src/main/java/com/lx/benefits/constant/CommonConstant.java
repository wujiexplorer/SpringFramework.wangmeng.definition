package com.lx.benefits.constant;

import java.math.BigDecimal;

public class CommonConstant {

    /**
     * spring 环境
     */
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_TEST = "test";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";
    /**
     * 日志追踪ID
     */
    public static final String TRACE_LOG_ID = "traceLogId";

    public static final String PARAMS = "params";
    public static final String SIGN = "sign";
    public static final String OS = "os";
    public static final String IS_APP = "isApp";
    public static final String FROM_WEB = "fromWeb";
    public static final String FORMAT = "format";
    public static final String VERSION = "version";
    public static final String CHANNEL = "channel";
    public static final String ACCOUNT_ID = "accountId";
    public static final String TOKEN = "token";

    /**
     * 数字100
     */
    public static final BigDecimal BIG_DECIMAL_100 = BigDecimal.valueOf(100);
    /**
     * 数字小数位
     */
    public static final int RATE_SCALE = 6;

    /**
     * 一周的天数
     */
    public static final int DAYS_OF_WEEK = 7;


    /**
     * 物流编号最长的长度
     */
    public static final int MAX_LOGISTICS_NUMBER_LENGTH = 20;

    /**
     * 手机号格式正则表达式
     */
    public static final String MOBILE_REGEX = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";

    /**
     * 邮件地址格式正则表达式
     */
    public static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$";

    /**
     * QQ号格式正则表达式
     */
    public static final String QQ_REGEX = "^[1-9][0-9]{5,}$";

    /**
     * 汉字 unicode范围
     */
    public static final String CHINESE_UNICODE_REGEX = "[\\u4E00-\\u9FA5]";

    /**
     * 大于0的最多保留两位小数的数字 正则表达式
     */
    public static final String POSITIVE_NUMBER_REGEX = "^[1-9]\\d*(\\.\\d{1,2})?|0.\\d?[1-9]$";


    /**
     * Emoji表情插入数据库报错信息
     */
    public static final String EMOJI_DB_ERROR_MESSAGE = "Incorrect string value";

    /**
     * 退款编号格式
     */
    public static final String REFUND_NUMBER_REGEX = "^TK[0-9]{15}$";
    /**
     * 订单编号格式
     */
    public static final String ORDER_NUMBER_REGEX = "^[0-9]{15}$";

    /**
     * 最大excel导出数量
     */
    public static final Integer MAX_EXCEL_EXPORT_ROWS = 100000;


    //通用数字
    public static final int COMMON_10 = 10;
    public static final int COMMON_20 = 20;
    public static final int COMMON_30 = 30;
    public static final int COMMON_100 = 100;
    public static final int COMMON_255 = 255;

    /**
     * 平台使用字符编码
     */
    public static final int IS_SYSTEM_MAINTENANCE = 1;

    /**
     * 平台使用字符编码
     */
    public static final String CHARACTER_ENCODING = "UTF-8";
    public static final String EXCEPTION_TIP = "服务器开小差了，请稍后再试";

    /**
     * 中国大陆区号
     */
    public static final int ADDRESS_CODE = 86;
}
