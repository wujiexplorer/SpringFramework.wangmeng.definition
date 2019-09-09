package com.lx.benefits.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.constants.AcountStatus;
import com.lx.benefits.bean.entity.agent.AgentAccountInfo;
import com.lx.benefits.bean.util.EncryptUtil;
import com.lx.benefits.bean.util.SessionAgentInfo;
import com.lx.benefits.bean.util.SessionContextHolder;
import com.lx.benefits.bean.util.SessionTokenInfo;
import com.lx.benefits.constant.PlatformConstatnts;
import com.lx.benefits.service.agent.AgentService;

@Component("agentAdminAuthSecurityInterceptor")
public class AgentAdminAuthSecurityInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private AgentService agentService;

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
			throws Exception {

		String viewToken = httpServletRequest.getHeader(PlatformConstatnts.AGENT_TOKEN);
		if (null == viewToken || viewToken.isEmpty()) {
			return false;
		}
		SessionTokenInfo sessionTokenInfo = EncryptUtil.decodeToken(viewToken);
		if (null == sessionTokenInfo || null == sessionTokenInfo.getSecret() || null == sessionTokenInfo.getToken()
				|| sessionTokenInfo.getToken().isEmpty() || sessionTokenInfo.getSecret().length() != 32) {
			return false;
		}
		String sessionInfo = EncryptUtil.aesDecrypt(sessionTokenInfo.getToken(), sessionTokenInfo.getSecret());
		SessionAgentInfo sessionAgentInfo = JSON.parseObject(sessionInfo, new TypeReference<SessionAgentInfo>() {
		});
		if (null != sessionAgentInfo && null != sessionAgentInfo.getAgentId() && sessionAgentInfo.getAgentId() > 0
				&& !StringUtils.isEmpty(sessionAgentInfo.getLoginName())) {
			AgentAccountInfo agentInfo = agentService.getAgentInfo(sessionAgentInfo.getAgentId());
			if (agentInfo == null) {
				return false;
			}
			if (AcountStatus.FREEZE.getStatus().equals(agentInfo.getAccountStatus())) {
				throw new BusinessException("您的账号已被冻结,请联系管理员!");
			}
			// set session
			SessionContextHolder.setSessionInfo(sessionAgentInfo);
			// set header
			httpServletResponse.setHeader("Access-Control-Expose-Headers", PlatformConstatnts.EXPOSE_HEADERS);
			httpServletResponse.setHeader("Access-Control-Allow-Headers", PlatformConstatnts.ALLOW_HEADERS);
			httpServletResponse.setHeader(PlatformConstatnts.AGENT_TOKEN, viewToken);
			httpServletResponse.setHeader(PlatformConstatnts.AGENT_NAME, sessionAgentInfo.getLoginName());
			return true;
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {
	}
}
