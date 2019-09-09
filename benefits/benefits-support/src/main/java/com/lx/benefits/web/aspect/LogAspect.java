package com.lx.benefits.web.aspect;


import com.alibaba.fastjson.JSON;
import com.apollo.common.utils.ClientIPUtil;
import com.apollo.common.utils.base.LogUtil;
import com.lx.benefits.bean.util.SessionContextHolder;

import nl.bitwalker.useragentutils.UserAgent;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


/**
 * 切点类
 */
@Aspect
public class LogAspect {
    public static final String TRACE_ID_KEY = "traceId";

    @Pointcut("execution(* com.lx.benefits.web.controller..*Controller.*(..))")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object doAroundAspect(ProceedingJoinPoint pjp) throws Throwable {
        MDC.put(TRACE_ID_KEY, UUID.randomUUID().toString().replaceAll("-","").substring(0,16));

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("USER-AGENT") == null ? "未知" : request.getHeader("USER-AGENT"));
        //读取session中的用户
        String username = SessionContextHolder.getCurrentLoginName();
        //获取用户请求方法的参数并序列化为JSON格式字符串
        String params = Arrays.toString( pjp.getArgs()).replace("\n",",");
        //请求的IP
        String ip = ClientIPUtil.getIpAddress(request);
        String uri = request.getRequestURI();
        String controllerName = getControllerName(pjp.getTarget().getClass().getName());
        String methodName = pjp.getSignature().getName();


        long nowTime = System.currentTimeMillis();
        Object object = null;
        try {
            object = pjp.proceed(pjp.getArgs());
            return object;
        } finally {
            try {
                LogUtil.info("开始请求调用: 耗时:{}ms, {}\t方法:{}\t用户登陆账号:{}\t客户端:{}\t源地址:{}\t参数:{}",
                        (System.currentTimeMillis() - nowTime),
                        uri,
                        controllerName + "." + methodName,
                        username,
                        userAgent.getOperatingSystem(),
                        ip,
                        params
                );
                LogUtil.info("响应结果为：{}", object == null ? "空结果" : JSON.toJSONString(object));
            } catch (Exception e) {
                //记录本地异常日志
                LogUtil.error("异常信息:{}", e.getMessage(), e);
            }
        }

    }

    private String getControllerName(String controllerPackageName){
        List<String> names = Arrays.asList(controllerPackageName.split("\\."));
        return names.get(names.size() - 1);
    }

}
