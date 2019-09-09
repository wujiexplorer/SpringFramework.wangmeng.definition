package com.lx.benefits.service.jd.impl;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.TypeReference;
import com.lx.benefits.bean.dto.jd.exception.ServiceException;
import com.lx.benefits.bean.dto.jd.res.JDResult;
import com.lx.benefits.bean.dto.jd.token.JDToken;
import com.lx.benefits.bean.dto.jd.token.JdAccessToken;
import com.lx.benefits.bean.dto.jd.utils.AssertUtil;
import com.lx.benefits.bean.dto.jd.utils.DateUtil;
import com.lx.benefits.bean.dto.jd.utils.MD5Util;
import com.lx.benefits.bean.util.HttpProtocolUtil;
import com.lx.benefits.mapper.jd.JdAccessTokenMapper;
import com.lx.benefits.service.jd.IJDAccessTokenService;
import com.lx.benefits.service.jd.JDConfigService;
import com.lx.benefits.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * User: fan
 * Date: 2019/02/25
 * Time: 18:26
 */
@Service
public class JDAccessTokenServiceImpl extends JDConfigService implements IJDAccessTokenService {

    private static final Logger logger = LoggerFactory.getLogger(JDAccessTokenServiceImpl.class);

    private String grant_type = "access_token";

    private String scope = "";

    private static String JD_ACCESS_TOKEN_CACHE_KEY = "new_jd_access_token_cache_key_";

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    private JdAccessTokenMapper jdAccessTokenMapper;

    @Override
    public String getToken() {
        try {
            Date cur = new Date();
            JdAccessToken jdAccessToken = redisUtils.get(JD_ACCESS_TOKEN_CACHE_KEY,JdAccessToken.class);
            String x = validateTokenExpired(cur, jdAccessToken);
            if (x != null) {
                return x;
            }
            jdAccessToken = jdAccessTokenMapper.getToken();
            x = validateTokenExpired(cur, jdAccessToken);
            if (x != null) {
                return x;
            }
            JDResult<JDToken> jdTokenJDResult = getJdTokenJDFromJD();
            if (!jdTokenJDResult.isSuccess()) {
                logger.error("GET_JD_ACCESS_TOKEN_ERROR_RESULT=" + JSON.toJSONString(jdTokenJDResult));
                throw new ServiceException("GET_JD_ACCESS_TOKEN_ERROR");
            }
            JdAccessToken token = insertJdAccessToken(cur, jdTokenJDResult);
            addToCache(jdAccessToken);
            return token.getAccessToken();
        } catch (Exception e) {
            logger.error("JD_GET_TOKEN_ERROR ", e);
            throw new ServiceException("GET_JD_ACCESS_TOKEN_ERROR");
        }
    }


    @Override
    public String freshToken() throws Exception {
        JDResult<JDToken> jdTokenJDResult = refreshJdTokenJDFromJD();
        if (!jdTokenJDResult.isSuccess()) {
            logger.error("JD_REFRESH_TOKEN_ERROR:" + JSON.toJSONString(jdTokenJDResult));
            return null;
        }
        JdAccessToken jdAccessToken = insertJdAccessToken(new Date(), jdTokenJDResult);
        addToCache(jdAccessToken);

        return jdAccessToken.getAccessToken();
    }

    @Override
    public JDResult<JDToken> refreshJdTokenJDFromJD() throws Exception {
        JdAccessToken jdAccessToken = jdAccessTokenMapper.getToken();
        if (jdAccessToken == null) {
            return getJdTokenJDFromJD();
        }
        Map<String, String> param = new HashMap<>();
        param.put("client_id", getClient_id());
        param.put("client_secret", getClient_secret());
        param.put("refresh_token", jdAccessToken.getRefreshToken());
        String resStr = HttpProtocolUtil.postData(getRefresh_access_token_url(), param, CHARSET);
        logger.info("JD_REFRESH_TOKEN_RESULT:" + resStr);
        JDResult<JDToken> jdTokenJDResult = JSON.parseObject(resStr, new TypeReference<JDResult<JDToken>>() {
        });
        return jdTokenJDResult;
    }

    private String validateTokenExpired(Date cur, JdAccessToken jdAccessToken) throws Exception {
        if (jdAccessToken != null) {
            if (new Date(jdAccessToken.getGetTime().getTime() + jdAccessToken.getExpiresIn() * 1000L).before(cur)) {
                logger.info("JD_ACCESS_TOKEN_NEED_REFRESH_TOKEN");
                return freshToken();
            }
            return jdAccessToken.getAccessToken();
        }
        return null;
    }

    private JdAccessToken insertJdAccessToken(Date cur, JDResult<JDToken> jdTokenJDResult) {
        JDToken jdToken = jdTokenJDResult.getResult();
        JdAccessToken token = new JdAccessToken();
        token.setGetTime(new Date(jdToken.getTime()));
        token.setUpdateUser("system");
        token.setUpdateTime(cur);
        token.setCreateUser("system");
        token.setCreateTime(cur);
        token.setAccessToken(jdToken.getAccess_token());
        token.setRefreshToken(jdToken.getRefresh_token());
        token.setExpiresIn(jdToken.getExpires_in());
        token.setRefreshTokenExpires(jdToken.getRefresh_token_expires());
        token.setUid(jdToken.getUid());
        jdAccessTokenMapper.insert(token);
        return token;
    }

    private boolean addToCache(JdAccessToken jdAccessToken) {
        return redisUtils.set(JD_ACCESS_TOKEN_CACHE_KEY, jdAccessToken, 60 * 60 * 24);
    }

    @Override
    public JDResult<JDToken> getJdTokenJDFromJD() throws Exception {
        AssertUtil.notBlank(getClient_id(), "client_id blank");
        AssertUtil.notBlank(getClient_secret(), "client_secret blank");
        AssertUtil.notBlank(getUsername(), "username blank");
        AssertUtil.notBlank(getPassword(), "password blank");
        String timestamp = DateUtil.format(new Date(), DateUtil.NEW_FORMAT);
        String sign_ori = getClient_secret() + timestamp + getClient_id() + getUsername() + MD5Util.encrypt(getPassword())
                + grant_type + scope + getClient_secret();
        logger.info(sign_ori);
        String sign = MD5Util.encrypt(sign_ori).toUpperCase();

        Map<String, String> param = new HashMap<>();
        param.put("grant_type", "access_token");
        param.put("client_id", getClient_id());
        param.put("client_secret", getClient_secret());
        param.put("username", getUsername());
        param.put("password", MD5Util.encrypt(getPassword()));
        param.put("timestamp", timestamp);
        param.put("scope", scope);
        param.put("sign", sign);

        String result = HttpProtocolUtil.postData(getAccess_token_url(), param, "UTF-8");

        logger.info(" JD_GET_TOKEN_RESULT=" + result);
        JDResult<JDToken> resultJDResult = JSON.parseObject(result, new TypeReference<JDResult<JDToken>>() {
        });
        if (!resultJDResult.isSuccess()) {
            logger.error("JD_GET_TOKEN_ERROR.RESULT:" + result);
            return null;
        }
        return resultJDResult;
    }

}
