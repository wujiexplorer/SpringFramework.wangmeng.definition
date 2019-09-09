package com.lx.benefits.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lx.benefits.bean.util.EncryptUtil;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.bean.util.SessionFuliInfo;
import com.lx.benefits.bean.util.SessionTokenInfo;
import com.lx.benefits.constant.PlatformConstatnts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: fan
 * Date: 2019/03/20
 * Time: 00:01
 */
@Component
public class PowerInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String USERPOWER_KEY = "USERPOWER:%s/%s";

    private static final Pattern pattern = Pattern.compile(".*/\\d+$");

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (true) {
            return  true;
        }
        String viewToken = httpServletRequest.getHeader(PlatformConstatnts.FULI_TOKEN);
        SessionTokenInfo sessionTokenInfo = EncryptUtil.decodeToken(viewToken);
        if (null == sessionTokenInfo || null == sessionTokenInfo.getSecret() || null == sessionTokenInfo.getToken() || sessionTokenInfo.getToken().isEmpty() || sessionTokenInfo.getSecret().length() != 32) {
            return false;
        }

        String sessionInfo = EncryptUtil.aesDecrypt(sessionTokenInfo.getToken(), sessionTokenInfo.getSecret());
        SessionFuliInfo sessionFuliInfo = JSON.parseObject(sessionInfo, new TypeReference<SessionFuliInfo>() {
        });
        String requestURL = httpServletRequest.getRequestURI();
        if (httpServletRequest.getMethod().equals("GET")) {
            Matcher m = pattern.matcher(requestURL);
            if (m.matches()) {
                requestURL = requestURL.substring(0, requestURL.lastIndexOf("/"));
            }
        }

        String key = String.format(USERPOWER_KEY,sessionFuliInfo.getAdminId(),sessionFuliInfo.getLoginName());
        if (redisTemplate.opsForHash().get(key, requestURL) != null) {
            return true;
        }
        String json = JSON.toJSONString(Response.fail("无权限操作"));
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().print(json);
        httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();
        return false;
    }
}
