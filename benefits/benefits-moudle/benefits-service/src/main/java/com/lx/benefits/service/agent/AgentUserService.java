package com.lx.benefits.service.agent;

import com.lx.benefits.bean.util.SessionAgentInfo;

public interface AgentUserService {

	/**
	 * 重置代理商密码
	 * 
	 * @param agentId
	 * @param password
	 */
	void resetAgentPassword(Integer agentId, String password);

	/**
	 * 登录
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 */
	SessionAgentInfo login(String loginName, String password);

	/**
	 * 修改代理商密码
	 * 
	 * @param loginName
	 * @param oldPassword
	 * @param password
	 */
	void modifyPassword(String loginName, String oldPassword, String password);

}
