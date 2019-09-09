package com.lx.benefits.service.client;

import com.lx.benefits.bean.entity.ent.ClientInfo;
import com.lx.benefits.bean.vo.client.ClientAccessToken;

public interface ClientService {

	/**
	 * 获取授权码
	 * 
	 * @param clientId
	 * @param clientSecret
	 * @return
	 */
	ClientAccessToken getAccessToken(String clientId, String clientSecret);

	/**
	 * 根据授权码获取客户端信息
	 * 
	 * @param accessToken 授权码
	 * @return
	 */
	ClientInfo getClientInfoByAccessToken(String accessToken);

}
