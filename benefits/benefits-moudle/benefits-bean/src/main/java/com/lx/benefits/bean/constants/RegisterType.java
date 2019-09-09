package com.lx.benefits.bean.constants;

/**
 * 注册方式
 * 
 * @author qixian
 *
 */
public enum RegisterType {
	REGISTERED_APPLY("注册申请"), //
	AGENT_RECOMMEND("代理商引荐"), //
	ENTERPRISE_UPGRADE("企业升级"), //
	INTERNAL_OPENING("内部开设"), //
	;

	private String type;

	private RegisterType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

}
