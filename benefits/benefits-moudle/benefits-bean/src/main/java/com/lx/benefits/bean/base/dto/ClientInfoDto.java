package com.lx.benefits.bean.base.dto;

import javax.validation.constraints.NotEmpty;

public class ClientInfoDto {

	@NotEmpty(message="第三方平台名称不能为空")
	private String clientName;
	@NotEmpty(message="URL不能为空")
	private String url;
	@NotEmpty(message="公司编码不能为空")
	private String orgCode;

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

}
