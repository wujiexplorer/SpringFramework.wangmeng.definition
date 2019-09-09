package com.lx.benefits.bean.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("代理商Session信息")
@Data
public class SessionAgentInfo implements SessionInfo{

	@ApiModelProperty(value = "代理商Id")
	private Integer agentId;

	@ApiModelProperty(value = "代理商登录用户名")
	private String loginName;

	@ApiModelProperty(value = "代理商名称")
	private String agentName;

}