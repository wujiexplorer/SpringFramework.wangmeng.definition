package com.lx.benefits.config.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.ImmutableSet;
import com.lx.benefits.bean.dto.enterpriseadmin.employee.EmployeeInfoDto;
import com.lx.benefits.bean.util.*;
import com.lx.benefits.constant.PlatformConstatnts;
import com.lx.benefits.service.employeeuserinfo.EmployeeUserInfoService;
import nl.bitwalker.useragentutils.OperatingSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Set;

@Component("enterpriseAuthSecurityInterceptor")
public class EnterpriseAuthSecurityInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(EnterpriseAuthSecurityInterceptor.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private EmployeeUserInfoService employeeUserInfoService;

    Set<OperatingSystem> operatingSystemSet = ImmutableSet.of(
            OperatingSystem.MAC_OS_X,
            OperatingSystem.WINDOWS,
            OperatingSystem.WINDOWS_7,
            OperatingSystem.WINDOWS_XP,
            OperatingSystem.WINDOWS_VISTA,
            OperatingSystem.WINDOWS_2000,
            OperatingSystem.WINDOWS_98
            );


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //TODO 微信登录操作
        String yianToken = httpServletRequest.getHeader(PlatformConstatnts.YIAN_TOKEN);
        log.info("ENTERPRISE_AUTH_SECURITY_INTERCEPTOR:PRE_HANDLE: yian-cache={}, url = {}, headers={}",
                yianToken, httpServletRequest.getRequestURI(), getHeaders(httpServletRequest));
        if (null != yianToken && !yianToken.isEmpty()) {
            Object token = redisTemplate.opsForValue().get(String.format("lx:%s", yianToken));
            String yianTokenVal = (token == null) ? null : token.toString();
            log.info("ENTERPRISE_AUTH_SECURITY_INTERCEPTOR:PRE_HANDLE: yianTokenVal={}", yianTokenVal);
            if (null != yianTokenVal) {
                JSONObject jsonObject = JsonUtils.getObj(yianTokenVal, new TypeReference<JSONObject>() {
                });
                log.info("ENTERPRISE_AUTH_SECURITY_INTERCEPTOR:PRE_HANDLE: jsonObject={}", jsonObject);
                if (null != jsonObject) {
                    Long userId = jsonObject.getLong("uid");
                    log.info("ENTERPRISE_AUTH_SECURITY_INTERCEPTOR:PRE_HANDLE: userId={}", userId);
                    if (null != userId && userId > 0) {
                        EmployeeInfoDto employeeInfoDto = employeeUserInfoService.findByEmployeeId(userId, false);
                        if (null != employeeInfoDto) {
                            log.info("ENTERPRISE_AUTH_SECURITY_INTERCEPTOR:PRE_HANDLE: employeeInfoDto={}", employeeInfoDto.toString());
                            SessionShopInfo sessionShopInfo = new SessionShopInfo();
                            sessionShopInfo.setEmployeeId(employeeInfoDto.getEmployeeId());
                            sessionShopInfo.setEmployeeName(employeeInfoDto.getEmployeeName());
                            sessionShopInfo.setEmployeeNo(employeeInfoDto.getEmployeeNo());
                            sessionShopInfo.setLoginName(employeeInfoDto.getLoginName());
                            sessionShopInfo.setEnterprId(employeeInfoDto.getEnterprId());
                            sessionShopInfo.setFromWeb(true);
                            SessionContextHolder.setSessionInfo(sessionShopInfo);
                            //set header
                            httpServletResponse.setHeader("Access-Control-Expose-Headers", PlatformConstatnts.EXPOSE_HEADERS);
                            httpServletResponse.setHeader("Access-Control-Allow-Headers", PlatformConstatnts.ALLOW_HEADERS);
                            httpServletResponse.setHeader(PlatformConstatnts.YIAN_TOKEN, yianToken);
                            httpServletResponse.setHeader(PlatformConstatnts.YIAN_NAME, sessionShopInfo.getLoginName());
                            return true;
                        }
                    }
                }
            }
        } else {
            String viewToken = httpServletRequest.getHeader(PlatformConstatnts.EMPLOYEE_TOKEN);
            if (null == viewToken || viewToken.isEmpty()) {
                return false;
            }
//            String redisToken  = redisUtils.get(viewToken);
//            if (null == redisToken || redisToken.isEmpty()) {
//                return false;
//            }
            SessionTokenInfo sessionTokenInfo = EncryptUtil.decodeToken(viewToken);
            if (null == sessionTokenInfo || null == sessionTokenInfo.getSecret() || null == sessionTokenInfo.getToken() || sessionTokenInfo.getToken().isEmpty() || sessionTokenInfo.getSecret().length() != 32) {
                return false;
            }
            String sessionInfo = EncryptUtil.aesDecrypt(sessionTokenInfo.getToken(), sessionTokenInfo.getSecret());
            SessionShopInfo sessionShopInfo = JSON.parseObject(sessionInfo, new TypeReference<SessionShopInfo>() {
            });
            if (null != sessionShopInfo && null != sessionShopInfo.getEnterprId() && sessionShopInfo.getEnterprId() > 0 && null != sessionShopInfo.getLoginName() && !sessionShopInfo.getLoginName().isEmpty() && null != sessionShopInfo.getEmployeeId() && sessionShopInfo.getEmployeeId() > 0) {

//                UserAgent userAgent = UserAgent.parseUserAgentString(httpServletRequest.getHeader("USER-AGENT") == null ? "未知" : httpServletRequest.getHeader("USER-AGENT"));
//                if (operatingSystemSet.contains(userAgent.getOperatingSystem())) {
//                    sessionShopInfo.setFromWeb(true);
//                }
                //set session
                SessionContextHolder.setSessionInfo(sessionShopInfo);

                //set header
                httpServletResponse.setHeader("Access-Control-Expose-Headers", PlatformConstatnts.EXPOSE_HEADERS);
                httpServletResponse.setHeader("Access-Control-Allow-Headers", PlatformConstatnts.ALLOW_HEADERS);
                httpServletResponse.setHeader(PlatformConstatnts.EMPLOYEE_TOKEN, viewToken);
                httpServletResponse.setHeader(PlatformConstatnts.EMPLOYEE_NAME, sessionShopInfo.getLoginName());
                return true;
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

    private String getHeaders(HttpServletRequest request) {
        StringBuffer stringBuffer = new StringBuffer();
        Enumeration<String> eHeaders = request.getHeaderNames();
        if (eHeaders != null) {
            while (eHeaders.hasMoreElements()) {
                String headKey = eHeaders.nextElement();
                stringBuffer.append(headKey).append(":").append(request.getHeader(headKey)).append(";");
            }
        }
        return stringBuffer.toString();
    }
}
