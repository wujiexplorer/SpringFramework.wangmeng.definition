package com.lx.benefits.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lx.benefits.bean.util.EncryptUtil;
import com.lx.benefits.bean.util.SessionTokenInfo;
import com.lx.benefits.bean.vo.card.CustomerInfo;
import com.lx.benefits.constant.PlatformConstatnts;

@Component("customerAdminAuthSecurityInterceptor")
public class CustomerAdminAuthSecurityInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

		String viewToken = httpServletRequest.getHeader(PlatformConstatnts.CUSTOMER_TOKEN);
		if (null == viewToken || viewToken.isEmpty()) {
			return false;
		}
		SessionTokenInfo sessionTokenInfo = EncryptUtil.decodeToken(viewToken);
		if (null == sessionTokenInfo || null == sessionTokenInfo.getSecret() || null == sessionTokenInfo.getToken() || sessionTokenInfo.getToken().isEmpty()
				|| sessionTokenInfo.getSecret().length() != 32) {
			return false;
		}
		String sessionInfo = EncryptUtil.aesDecrypt(sessionTokenInfo.getToken(), sessionTokenInfo.getSecret());
		CustomerInfo customerInfo = JSON.parseObject(sessionInfo, new TypeReference<CustomerInfo>() {
		});
		if (null != customerInfo) {
			httpServletRequest.setAttribute("customerId", customerInfo.getCustomerId());
			httpServletResponse.setHeader("Access-Control-Expose-Headers", PlatformConstatnts.EXPOSE_HEADERS);
			httpServletResponse.setHeader("Access-Control-Allow-Headers", PlatformConstatnts.ALLOW_HEADERS);
			httpServletResponse.setHeader(PlatformConstatnts.CUSTOMER_TOKEN, viewToken);
			httpServletResponse.setHeader(PlatformConstatnts.CUSTOMER_NAME, customerInfo.getCustomerName());
			return true;
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
			throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
	}
}
