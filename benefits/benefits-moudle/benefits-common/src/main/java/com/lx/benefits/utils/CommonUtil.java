package com.lx.benefits.utils;

import com.google.common.collect.Maps;
import com.lx.benefits.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;


@Slf4j
public class CommonUtil {

    private static Pattern isMobile = compile("^[1][3,4,5,6,7,8,9][0-9]{9}$");

    private static Pattern isNumber = compile("[0-9]*");




    /**
     * 生成唯一发货单号 "SH" + orderId(11位) + ("S":供应商后台) + "001"(还没有使用分批发货的情况下固定为001，引入分批发货功能后根据count增长)
     * @param orderId
     * @return
     */
    public static String generateShipmentCode(Long orderId) {
        return "SH" + String.format("%011d",orderId) + "S001";
    }



    /**
     * 将DATE类型装换成字符串格式
     *
     * @param date
     * @param type 要转换成的字符串格式，如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2String(Date date, String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        return sdf.format(date);
    }

    /**
     * 将DATE类型装换成字符串格式
     *
     * @param date
     * @return
     */
    public static String date2String(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (date == null) {
            return "";
        }
        return sdf.format(date);
    }

    /**
     * 将DATE类型装换成字符串格式
     *
     * @param dateStr
     * @return
     */
    public static Date string2Date(String dateStr) {


        if (StringUtils.isNotBlank(dateStr)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                return sdf.parse(dateStr);
            } catch (ParseException e) {
                return null;
            }
        }
        return null;
    }
    /**
     * 生成一个唯一的UUID
     *
     * @return
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase().substring(0, 30);
    }

    /**
     * 根据单位是分的值 获取展示的元的价格
     *
     * @param centValue
     * @return
     */
    public static String getDisplayYuan(Integer centValue) {
        if (centValue == null) {
            return "";
        }
        BigDecimal b = BigDecimal.valueOf(centValue / 100F);
        BigDecimal one = new BigDecimal("1");
        return removeLastZero(String.valueOf(b.divide(one, 2, BigDecimal.ROUND_HALF_UP)));
    }

    private static String removeLastZero(Object floatingNumberObj) {
        String floatingNumber = String.valueOf(floatingNumberObj);
        if (floatingNumber.matches("\\d+\\.?\\d+")) {
            if (floatingNumber.contains(".")) {
                // 正则表达
                floatingNumber = floatingNumber.replaceAll("0+$", "");// 去掉后面无用的零
                floatingNumber = floatingNumber.replaceAll("\\.$", "");// 如小数点后面全是零则去掉小数点
            }
        }
        return floatingNumber;
    }

    /**
     * 空则替换
     */
    public static <T> T notNullOrElse(T data, T replacer) {
        if (data == null) {
            return replacer;
        }
        return data;
    }

    public static boolean hasEmoji(String content) {

        Pattern pattern = compile(".*[\\x{10000}-\\x{10FFFF}].*", UNICODE_CASE | CASE_INSENSITIVE);
        return pattern.matcher(content).find();
    }

    public static String toStringWithNull(Object value){
        if (value == null){
            return null;
        }
        return value.toString();
    }

    /**
     * 将http文件转换为二进制
     *
     * @param httpFile url
     * @return
     * @throws IOException
     */
    public static byte[] convertHttpFile2Bytes(String httpFile)
        throws IOException
    {
        URL url = new URL(httpFile);
        URLConnection urlConn = url.openConnection();
        urlConn.setConnectTimeout(3000);
        urlConn.setReadTimeout(2000);
        return convertInputStream2Bytes(urlConn.getInputStream());
    }

    /**
     * 将输入流转为字节数组格式的对象
     *
     * @param in 输入流对象
     * @return
     */
    public static byte[] convertInputStream2Bytes(InputStream in)
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int length = 0;
        byte[] buffer = new byte[1024];

        try
        {
            while ((length = in.read(buffer)) != -1)
            {
                out.write(buffer, 0, length);
            }

            return out.toByteArray();
        }
        catch (IOException e)
        {
            log.error("read from inputstream error.", e);
        }
        finally
        {
            try
            {
                out.close();
            }
            catch (IOException e)
            {
                log.error("close the outputstream error.", e);
            }
        }

        return null;
    }

    /**
     * 把字符串转换成md5
     *
     * @param str
     * @return
     */
    public static String strToMD5(String str)
    {
        try
        {
            byte[] input;
            input = str.getBytes(CommonConstant.CHARACTER_ENCODING);
            byte[] bytes = bytesToMD5(input);
            if(bytes == null){
                log.error("strToMD5编码结果为空:{}",str);
                return "";
            }
            return bytesToHex(bytes);
        }
        catch (UnsupportedEncodingException e)
        {
            log.error("strToMD5编码不支持!", e);
        }
        return "";
    }

    /**
     * 把字节数组转成16进位制数
     *
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes)
    {
        StringBuffer md5str = new StringBuffer();
        // 把数组每一字节换成16进制连成md5字符串
        int digital;
        for (int i = 4; i < bytes.length - 4; i++)
        {
            digital = bytes[i];
            if (digital < 0)
            {
                digital += 256;
            }
            if (digital < 16)
            {
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString().toUpperCase();
    }

    /**
     * 把字节数组转换成md5
     *
     * @param input
     * @return
     */
    public static byte[] bytesToMD5(byte[] input)
    {
        byte[] buff = null;
        try
        {
            // 创建一个提供信息摘要算法的对象，初始化为md5算法对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算后获得字节数组
            buff = md.digest(input);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
        return buff;
    }

    /**
     * 获得一个UUID
     *
     * @return String UUID
     */
    public static synchronized String getUUID()
    {
        String s = UUID.randomUUID().toString();
        // 去掉“-”符号
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

    /**
     * 是否微信brower
     *
     * @param userAgent
     * @return
     */
    public static boolean isWeiXinBrower(String userAgent)
    {

        boolean flag = false;
        if (StringUtils.isBlank(userAgent))
        {
            return flag;
        }
        userAgent = userAgent.toLowerCase();

        if (userAgent.contains("micromessenger"))
        {
            flag = true;
        }
        return flag;
    }

    /**
     * 获取Cookie集合
     * @param request
     * @return
     */
    public static Map<String, Cookie> getCookieMap(HttpServletRequest request)
    {
        Map<String, Cookie> cookieMap = Maps.newHashMap();
        Cookie[] cookies = request.getCookies();
        if (null != cookies)
        {
            for (Cookie cookie : cookies)
            {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    /**
     * 获得一个UUID
     *
     * @return String UUID
     */
    public static synchronized  String getShareKeyUUID()
    {
        String s = UUID.randomUUID().toString();
        // 去掉“-”符号
        return (s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24)).substring(4, 14);
    }

    /**
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str)
    {
        Matcher m = null;
        boolean b = false;
        m = isMobile.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 将字符串格式装换成DATE类型
     *
     * @param date
     * @param type 被转换的字符串格式，如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date string2Date(String date, String type)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        Date time = null;
        try
        {
            time = sdf.parse(date.replace(".0", ""));
        }
        catch (ParseException e)
        {
            log.error("字符串格式装换成DATE类型出错", e);
        }
        return time;
    }

    /**
     * 生成用户的16位大写md5密钥
     *
     * @param accountId
     * @return
     */
    public static String generateAccountSign(int accountId)
        throws Exception
    {
        String signSuffix = "JiaGengDuoQian18664573290";
        String plainText = accountId + signSuffix;

        String sign = plainText;
        for (int i = 0; i < 3; i++)
        {
            sign = strToMD5(sign);
        }
        return sign;
    }

    /**
     * 获取ipV4地址
     *
     * @param request
     * @return
     */
    public static String getRemoteIpAddr(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.length() > 15)
        {
            if (ip.indexOf(",") > 0)
            {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

    /**
     * 利用正则表达式判断字符串是否是数字,正整数
     * @param str
     * @return
     */
    public static boolean isNumericByMatcher(String str)
    {
        Matcher isNum = isNumber.matcher(str);
        return isNum.matches();
    }

    /**
     * 生成指定长度的随机数字
     *
     * @param length
     * @return 验证通过返回true
     */
    public static String generateRandomCode(int length)
    {
        String result = "";
        for (int i = 0; i < length; i++)
        {
            result += nextInt(0, 9);
        }
        return result;
    }

    private static int nextInt(final int min, final int max)
    {
        Random rand = new Random();
        int tmp = rand.nextInt();
        if(tmp < 0) {
            tmp = -tmp;
        }
        return tmp % (max - min + 1) + min;
    }
}
