package com.wangmeng.tomcat;
/**
 * @author wangjun [wangjun8@xiaomi.com]
 * @date 2018-11-06
 * @version 1.0
 */
public class ServletMapping {
	
	private String servletName;
	private String url;
	private String className;
	
	public ServletMapping(String servletName, String url, String className) {
		super();
		this.servletName = servletName;
		this.url = url;
		this.className = className;
	}

	public String getServletName() {
		return servletName;
	}

	public void setServletName(String servletName) {
		this.servletName = servletName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
}
