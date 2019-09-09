package com.lx.benefits.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lx.benefits.bean.util.EncryptUtil;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionEnterpriseInfo;
import com.lx.benefits.bean.util.SessionTokenInfo;
import com.lx.benefits.constant.PlatformConstatnts;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("enterpriseAdminAuthSecurityInterceptor")
public class EnterpriseAdminAuthSecurityInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String viewToken = httpServletRequest.getHeader(PlatformConstatnts.ENTERPRISE_TOKEN);
        if (null == viewToken || viewToken.isEmpty()) {
            return false;
        }
        SessionTokenInfo sessionTokenInfo = EncryptUtil.decodeToken(viewToken);
        if (null == sessionTokenInfo || null == sessionTokenInfo.getSecret() || null == sessionTokenInfo.getToken() || sessionTokenInfo.getToken().isEmpty() || sessionTokenInfo.getSecret().length() != 32) {
            return false;
        }
        String sessionInfo = EncryptUtil.aesDecrypt(sessionTokenInfo.getToken(), sessionTokenInfo.getSecret());
        SessionEnterpriseInfo sessionEnterpriseInfo = JSON.parseObject(sessionInfo, new TypeReference<SessionEnterpriseInfo>() {
        });
        if (null != sessionEnterpriseInfo && null != sessionEnterpriseInfo.getEnterprId() && sessionEnterpriseInfo.getEnterprId() > 0 && null != sessionEnterpriseInfo.getLoginName() && !sessionEnterpriseInfo.getLoginName().isEmpty()) {
            //set session
            SessionContextHolder.setSessionInfo(sessionEnterpriseInfo);

            //set header
            httpServletResponse.setHeader("Access-Control-Expose-Headers", PlatformConstatnts.EXPOSE_HEADERS);
            httpServletResponse.setHeader("Access-Control-Allow-Headers", PlatformConstatnts.ALLOW_HEADERS);
            httpServletResponse.setHeader(PlatformConstatnts.ENTERPRISE_TOKEN, viewToken);
            httpServletResponse.setHeader(PlatformConstatnts.ENTERPRISE_NAME, sessionEnterpriseInfo.getLoginName());
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
