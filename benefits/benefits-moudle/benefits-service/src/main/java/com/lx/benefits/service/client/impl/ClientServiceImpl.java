package com.lx.benefits.service.client.impl;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.lx.benefits.bean.entity.ent.ClientInfo;
import com.lx.benefits.bean.entity.ent.ClientInfoCondition;
import com.lx.benefits.bean.vo.client.ClientAccessToken;
import com.lx.benefits.mapper.ent.ClientInfoMapper;
import com.lx.benefits.service.client.ClientService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientInfoMapper clientInfoMapper;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	private final int ACCESS_TOKEN_EXPIRE_IN = 24 * 60 * 60;// ACCESS_TOKEN过期时间（单位：秒）
	private final String ACCESS_TOKEN_KEY_FORMAT = "client_access_token_%s";// ACCESS_TOKEN过期时间，添加10秒的误差值（单位：秒）

	@Override
	public ClientAccessToken getAccessToken(String clientId, String clientSecret) {
		ClientInfoCondition example = new ClientInfoCondition();
		example.createCriteria().andClientIdEqualTo(clientId).andClientSecretEqualTo(clientSecret);
		List<ClientInfo> clientInfos = clientInfoMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(clientInfos)) {
			return null;
		}
		ClientInfo clientInfo = clientInfos.get(0);
		ClientAccessToken clientAccessToken = new ClientAccessToken();
		String accessToken = UUID.randomUUID().toString().replace("-", "");
		clientAccessToken.setAccessToken(accessToken);
		clientAccessToken.setExpiresIn(ACCESS_TOKEN_EXPIRE_IN);
		// 更新数据库
		ClientInfo udateRecorde = new ClientInfo();
		udateRecorde.setId(clientInfo.getId());
		udateRecorde.setToken(accessToken);
		udateRecorde.setExpires(System.currentTimeMillis() + clientAccessToken.getExpiresIn());
		udateRecorde.setExpiresIn(clientAccessToken.getExpiresIn());
		clientInfoMapper.updateById(udateRecorde);
		// 清除旧的token
		if (!StringUtils.isEmpty(clientInfo.getToken())) {
			stringRedisTemplate.delete(String.format(ACCESS_TOKEN_KEY_FORMAT, clientInfo.getToken()));
		}
		stringRedisTemplate.opsForValue().set(String.format(ACCESS_TOKEN_KEY_FORMAT, accessToken), JSON.toJSONString(clientInfo), ACCESS_TOKEN_EXPIRE_IN + 10,
				TimeUnit.SECONDS);// 添加10秒误差
		log.info("set AccessToken, clientId={}, AccessToken={}", clientId, accessToken);
		return clientAccessToken;
	}

	@Override
	public ClientInfo getClientInfoByAccessToken(String accessToken) {
		if (StringUtils.isEmpty(accessToken)) {
			return null;
		}
		String clientInfoStr = stringRedisTemplate.opsForValue().get(String.format(ACCESS_TOKEN_KEY_FORMAT, accessToken));
		return JSON.parseObject(clientInfoStr, ClientInfo.class);
	}

}
