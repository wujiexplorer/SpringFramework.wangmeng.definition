package com.lx.benefits.bean.util;

public class SessionContextHolder {

	private static final ThreadLocal<SessionInfo> SESSION_INFO = new ThreadLocal<>();

	public static void setSessionInfo(SessionInfo sessionInfo) {
		SESSION_INFO.set(sessionInfo);
	}

	public static SessionInfo getSessionInfo() {
		return SESSION_INFO.get();
	}

	public static String getCurrentLoginName() {
		SessionInfo sessionInfo = SESSION_INFO.get();
		if (sessionInfo != null) {
			return sessionInfo.getLoginName();
		}
		return null;
	}

	// 在请求响应结束阶段，通过顶层拦截器（AuthSecurityInterceptor）清空对应的用户信息
	public static void clearSessionInfo() {
		SESSION_INFO.remove();
	}

	/**
	 * 获取运营后台登录session信息
	 * 
	 * @return
	 */
	public static SessionFuliInfo getSessionFuliInfo() {
		SessionInfo sessionInfo = SESSION_INFO.get();
		if (sessionInfo != null && (sessionInfo instanceof SessionFuliInfo)) {
			return (SessionFuliInfo) sessionInfo;
		}
		return null;
	}

	/**
	 * 获取企业HR后台登录session信息
	 * 
	 * @return
	 */
	public static SessionEnterpriseInfo getSessionEnterpriseInfo() {
		SessionInfo sessionInfo = SESSION_INFO.get();
		if (sessionInfo != null && (sessionInfo instanceof SessionEnterpriseInfo)) {
			return (SessionEnterpriseInfo) sessionInfo;
		}
		return null;
	}

	/**
	 * 获取供应商后台登录session信息
	 * 
	 * @return
	 */
	public static SessionSupplierInfo getSessionSupplierInfo() {
		SessionInfo sessionInfo = SESSION_INFO.get();
		if (sessionInfo != null && (sessionInfo instanceof SessionSupplierInfo)) {
			return (SessionSupplierInfo) sessionInfo;
		}
		return null;
	}

	/**
	 * 获取企业商城员工登录session信息
	 * 
	 * @return
	 */
	public static SessionShopInfo getSessionEmployeeInfo() {
		SessionInfo sessionInfo = SESSION_INFO.get();
		if (sessionInfo != null && (sessionInfo instanceof SessionShopInfo)) {
			return (SessionShopInfo) sessionInfo;
		}
		return null;
	}

	// 处理代理商session信息
	public static SessionAgentInfo getSessionAgentInfo() {
		SessionInfo sessionInfo = SESSION_INFO.get();
		if (sessionInfo != null && (sessionInfo instanceof SessionAgentInfo)) {
			return (SessionAgentInfo) sessionInfo;
		}
		return null;
	}

}