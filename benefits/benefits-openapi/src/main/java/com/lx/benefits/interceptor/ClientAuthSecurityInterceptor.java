package com.lx.benefits.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.entity.ent.ClientInfo;
import com.lx.benefits.bean.util.Response;
import com.lx.benefits.service.client.ClientService;
import com.lx.benefits.web.attributes.FuliRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ClientAuthSecurityInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private ClientService clientService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (StringUtils.isEmpty(authorization)) {
			log.error("UNAUTH, URI={}", request.getRequestURI());
			throw new BusinessException(String.valueOf(Response.NOT_AUTH), "UNAUTH");
		}
		ClientInfo clientInfo;
		if (!authorization.startsWith("Bearer ") || (clientInfo = clientService.getClientInfoByAccessToken(authorization.substring(7))) == null) {
			log.error("ERROR_TOKEN, URI={}, TOKEN={}", request.getRequestURI(), authorization);
			throw new BusinessException(String.valueOf(Response.ERROR_TOKEN), "ERROR_TOKEN");
		}
		request.setAttribute(FuliRequestAttributes.CLIENT_INFO_ID, clientInfo.getClientId());
		request.setAttribute(FuliRequestAttributes.CLIENT_INFO_ORGCODE, clientInfo.getOrgCode());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
			throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
	}
}
