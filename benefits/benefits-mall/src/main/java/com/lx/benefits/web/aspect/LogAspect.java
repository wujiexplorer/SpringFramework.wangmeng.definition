package com.lx.benefits.web.aspect;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.apollo.common.utils.ClientIPUtil;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionShopInfo;

import lombok.extern.slf4j.Slf4j;
import nl.bitwalker.useragentutils.UserAgent;

/**
 * 切点类
 */
@Aspect
@Slf4j
public class LogAspect {
	public static final String TRACE_ID_KEY = "traceId";

	@Pointcut("execution(* com.lx.benefits.web.controller..*Controller.*(..))")
	public void pointCut() {

	}

	@Around("pointCut()")
	public Object doAroundAspect(ProceedingJoinPoint pjp) throws Throwable {
		MDC.put(TRACE_ID_KEY, UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16));

		HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("USER-AGENT") == null ? "未知" : request.getHeader("USER-AGENT"));
		// 读取session中的用户
		SessionShopInfo sessionShopInfo = SessionContextHolder.getSessionEmployeeInfo();
		String username = Objects.nonNull(sessionShopInfo) ? sessionShopInfo.getLoginName() : "";
		// 请求的IP
		String ip = ClientIPUtil.getIpAddress(request);
		String uri = request.getRequestURI();
		String controllerName = getControllerName(pjp.getTarget().getClass().getName());
		String methodName = pjp.getSignature().getName();
		long nowTime = System.currentTimeMillis();

		try {
			String argString = "[]";
			Object[] args = pjp.getArgs();
			if (args.length > 0) {
				MethodSignature signature = (MethodSignature) pjp.getSignature();
				String[] parameterNames = signature.getParameterNames();
				StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
				for (int i = 0; i < parameterNames.length; i++) {
					Object item = args[i];
					if (item != null && (item instanceof ServletRequest || item instanceof ServletResponse || item instanceof MultipartFile)) {
						continue;
					}
					stringJoiner.add(parameterNames[i] + "=" + JSON.toJSONString(item));
				}
				argString = stringJoiner.toString();
			}
			log.info("请求调用: {}, 方法:{}, 用户登陆账号:{}, 客户端:{}, 源地址:{}, 参数:{}", uri, controllerName + "." + methodName, username, userAgent.getOperatingSystem(), ip,
					argString);
		} catch (Exception e) {
			log.error("uri:{}, 异常信息:{}", uri, e.getMessage());
		}
		Object object = null;
		try {
			object = pjp.proceed(pjp.getArgs());
			return object;
		} finally {
			try {
				log.info("响应结束: 耗时:{}ms, 结果为:{}", System.currentTimeMillis() - nowTime, object == null ? "空结果" : JSON.toJSONString(object));
			} catch (Exception e) {
				log.error("uri:{}, 异常信息:{}", uri, e.getMessage());
			}
		}
	}

	private String getControllerName(String controllerPackageName) {
		List<String> names = Arrays.asList(controllerPackageName.split("\\."));
		return names.get(names.size() - 1);
	}
}
