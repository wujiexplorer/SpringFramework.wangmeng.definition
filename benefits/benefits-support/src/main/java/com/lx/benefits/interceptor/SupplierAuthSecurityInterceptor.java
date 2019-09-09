package com.lx.benefits.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lx.benefits.bean.util.EncryptUtil;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionSupplierInfo;
import com.lx.benefits.bean.util.SessionTokenInfo;
import com.lx.benefits.constant.PlatformConstatnts;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("supplierAuthSecurityInterceptor")
public class SupplierAuthSecurityInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String viewToken = httpServletRequest.getHeader(PlatformConstatnts.SUPPLIER_TOKEN);
        if (null == viewToken || viewToken.isEmpty()) {
            return false;
        }
        SessionTokenInfo sessionTokenInfo = EncryptUtil.decodeToken(viewToken);
        if (null == sessionTokenInfo || null == sessionTokenInfo.getSecret() || null == sessionTokenInfo.getToken() || sessionTokenInfo.getToken().isEmpty() || sessionTokenInfo.getSecret().length() != 32) {
            return false;
        }
        String sessionInfo = EncryptUtil.aesDecrypt(sessionTokenInfo.getToken(), sessionTokenInfo.getSecret());
        SessionSupplierInfo sessionSupplierInfo = JSON.parseObject(sessionInfo, new TypeReference<SessionSupplierInfo>() {
        });
        if (null != sessionSupplierInfo && null != sessionSupplierInfo.getSupplierId() && sessionSupplierInfo.getSupplierId() > 0 && null != sessionSupplierInfo.getLoginName() && !sessionSupplierInfo.getLoginName().isEmpty()) {
            //set session
            SessionContextHolder.setSessionInfo(sessionSupplierInfo);

            //set header
            httpServletResponse.setHeader("Access-Control-Expose-Headers", PlatformConstatnts.EXPOSE_HEADERS);
            httpServletResponse.setHeader("Access-Control-Allow-Headers", PlatformConstatnts.ALLOW_HEADERS);
            httpServletResponse.setHeader(PlatformConstatnts.SUPPLIER_TOKEN, viewToken);
            httpServletResponse.setHeader(PlatformConstatnts.SUPPLIER_NAME, sessionSupplierInfo.getLoginName());
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
