package com.lx.benefits.utils;

import com.alibaba.fastjson.JSONObject;
import com.lx.benefits.bean.dto.yianapi.cache.TokenCacheTO;
import com.lx.benefits.bean.enums.MResultInfo;
import com.lx.benefits.bean.exception.MobileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 缓存工具类
 * @author zhuss
 * @2016年1月3日 下午5:54:51
 */
@Service
public class TokenCacheHelper {

	private static final Logger log = LoggerFactory.getLogger(TokenCacheHelper.class);

	public static final String TOKEN_PREFIX = "lx:";

	//TOKEN存活时间 单位为秒
	public static final int TOKEN_LIVE = 365*86400;

	//TOKEN存活时间 单位为秒
	public static final int TOKEN_LIVE_2 = 120;

	@Autowired
	private RedisUtils  redisUtils;

	
	/**
	 * 设置Token
	 * @param token
	 */
	public void setTokenCache(String key , TokenCacheTO token){
		boolean result =  redisUtils.set(key, token, TOKEN_LIVE);
		if(!result){
			log.error("[缓存工具-设置Token 失败] = {}",MResultInfo.CACHE_SET_FAILED.message);
			throw new MobileException(MResultInfo.CACHE_SET_FAILED);
		}
		try{
			redisUtils.set(TOKEN_PREFIX+key, JSONObject.toJSONString(token), TOKEN_LIVE);
		}catch (Exception ex) {
			log.error("[SET_TOKEN_CACHE] -- error");
		}
	}

	
	/**
	 * 获取token对象值
	 * @param key
	 * @return
	 */
	public TokenCacheTO getTokenCache(String key){
		return redisUtils.get(key,TokenCacheTO.class);
	}

	/**
	 * 删除token
	 * @param key
	 */
	public void delToken(String key){
		boolean result =  redisUtils.delete(key);
		if(result){
			log.error("[缓存工具-删除Token 失败] = {}",MResultInfo.CACHE_DEL_FAILED.message);
			throw new MobileException(MResultInfo.SYSTEM_ERROR);
		}
	}
}
