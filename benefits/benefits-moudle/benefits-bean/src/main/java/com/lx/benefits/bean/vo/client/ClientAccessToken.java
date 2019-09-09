package com.lx.benefits.bean.vo.client;

import lombok.Data;

@Data
public class ClientAccessToken {
	private String accessToken;
	private Integer expiresIn;// 单位：秒

}
