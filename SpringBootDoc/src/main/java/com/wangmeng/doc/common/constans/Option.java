package com.wangmeng.doc.common.constans;
/**
 * 选项词典
 * 创建者 小柒2012
 * 创建时间	2017年9月7日
 */
public enum Option {
	ENABLED_REGISTER("ENABLED_REGISTER","是否启用注册"),
	ENABLE_DOCUMENT_HISTORY("ENABLE_DOCUMENT_HISTORY","是否启用文档历史"),
	ENABLED_CAPTCHA("ENABLED_CAPTCHA","是否启用验证码"),
	ENABLE_ANONYMOUS("ENABLE_ANONYMOUS","启用匿名访问"),
	SITE_NAME("SITE_NAME","站点名称");
	
	private String code;
	private String name;
	
	private Option( String code,String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(String code,String name) {
		for (Option c : Option.values()) {
			if (c.getCode().equals(code)) {
				return c.name;
			}
		}
		return null;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
