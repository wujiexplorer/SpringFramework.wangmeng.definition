package com.lx.benefits.bean.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author unknow on 2018-12-19 11:40.
 */
public class CookieUtil {

    private final static Logger logger = LoggerFactory.getLogger(CookieUtil.class);

    /**
     * 设置cookie信息
     * @param request     HttpServletRequest
     * @param response    HttpServletResponse
     * @param name        name
     * @param value       value
     * @param expiry      expiry
     * @param path        path
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, int expiry, String path) {
        boolean isExist = false;
        if (null != request && null != request.getCookies()) {
            try {
                for (Cookie cookie : request.getCookies()) {
                    if (null != cookie && name.equals(cookie.getName())) {
                        cookie.setValue(value);
                        cookie.setPath(path);
                        cookie.setMaxAge(expiry);
                        try{
                            cookie.setHttpOnly(true);   //- 防XSS
                        } catch (Exception ex) {
                            logger.warn("setCookie cookie.setHttpOnly ", ex);
                        }
                        response.addCookie(cookie);
                        isExist = true;
                    }
                }    
            } catch (Exception e) {
                logger.error("setCookie cookie.addCookie ", e);
            }
        }
        if (!isExist) {
            addCookie(response, name, value, expiry, path);
        }
    }
    
    /**
     * 设置cookie信息
     * @param response    HttpServletResponse
     * @param name        name
     * @param value       value
     * @param expiry      过期时间,单位秒
     * @param path        cookie路径
     */
    private static void addCookie(HttpServletResponse response, String name, String value, int expiry, String path) {
        try {
            //特殊字符需要编码
            Cookie cookie = new Cookie(name, URLEncoder.encode(value, "UTF-8"));
            cookie.setMaxAge(expiry);
            cookie.setPath(path);
            try{
                cookie.setHttpOnly(true);   //- 防XSS
            } catch (Exception ex) {
                logger.warn("addCookie cookie.setHttpOnly ", ex);
            }
            response.addCookie(cookie);
        } catch (Exception e) {
            logger.error("addCookie cookie.addCookie ", e);
        }
    }
    
    /**
     * 删除Cookie
     * @param request     HttpServletRequest
     * @param response    HttpServletResponse
     * @param name        name
     * @param path        path
     * @return     boolean
     */
    public static boolean deleteCookie(HttpServletRequest request, HttpServletResponse response, String name, String path) {
        try {
            if (null != request && null != request.getCookies()) {
                for (Cookie cookie : request.getCookies()) {
                    if (null != cookie && name.equals(cookie.getName())) {
                        cookie.setMaxAge(0);
                        cookie.setValue("");
                        cookie.setPath(path);
                        try{
                            cookie.setHttpOnly(true);   //- 防XSS    
                        }catch (Exception ex) {
                            logger.warn("deleteCookie cookie.setHttpOnly ", ex);
                        }
                        response.addCookie(cookie);
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("deleteCookie cookie.addCookie ", e);
        }
        return false;
    }

    /**
     * 获取Cookie对应的值
     * @param request  HttpServletRequest
     * @param name     name
     * @return String
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        if (null != request && null != request.getCookies()) {
            try {
                for (Cookie cookie : request.getCookies()) {
                    if (null != cookie && name.equals(cookie.getName())) {
                        return URLDecoder.decode(cookie.getValue(), "UTF-8");
                    }
                }
            } catch (Exception e) {
                logger.error("getCookieValue cookie.setHttpOnly ", e);
            }
        }
        return null;
    }

}
