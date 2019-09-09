package com.lx.benefits.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * Created by lidongri on 2018/11/16.
 */
public class AppLoginTokenGenUtil {

    private static Logger logger = LoggerFactory.getLogger(AppLoginTokenGenUtil.class);

    public static String getAppLoginToken(Long id,String mobile,String pwd){
        StringBuffer tokenStr = new StringBuffer();
        tokenStr.append(id).append(mobile).append(pwd).append(PasswordHelper.$secretkey);
        try {
            String token = PasswordHelper.md5(tokenStr.toString());
            logger.info("user token:"+token);
            return token;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
