package com.lx.benefits.helper;

import com.lx.benefits.bean.dto.yianapi.cache.TokenCacheTO;
import com.lx.benefits.bean.enums.MResultInfo;
import com.lx.benefits.bean.exception.MobileException;
import com.lx.benefits.service.employeeuserinfo.EmployeeUserInfoService;
import com.lx.benefits.utils.TokenCacheHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 认证工具类
 * @author zhuss
 * @2016年1月3日 下午6:02:53
 */
@Service
public class AuthHelper {
	
	private static final Logger log = LoggerFactory.getLogger(AuthHelper.class);
	
	public static final String APP_SIGN = "XG_APP";
	
	public static final String WX_TOKEN = "xgtoken";

	public static final Map<Long,Integer> MEMBER_ACCESS_MAP = new CacheMap<Long,Integer>(1000*60*30);
	@Autowired
	private TokenCacheHelper cacheHelper;

	@Autowired
	private EmployeeUserInfoService employeeUserInfoService;

	/**
	 * 验证Token
	 * @return
	 */
	public TokenCacheTO authToken(String tokenKey){
		if(StringUtils.isBlank(tokenKey)) {
			log.error("[验证Token error] = {}",MResultInfo.TOKEN_NULL.message);
			throw new MobileException(MResultInfo.ACCOUNT_TIMEOUT);
		}
		TokenCacheTO token = cacheHelper.getTokenCache(tokenKey);
		if(null == token){
			log.error("[验证Token error] = {}",MResultInfo.TOKEN_NO_EXIST.message);
			throw new MobileException(MResultInfo.ACCOUNT_TIMEOUT);	
		}
		if(null == token.getUid()){
			log.error("[验证Token error] = {}",MResultInfo.TOKEN_NO_USER.message);
			throw new MobileException(MResultInfo.ACCOUNT_TIMEOUT);	
		}
		Integer count = MEMBER_ACCESS_MAP.get(token.getUid());
		if(count==null){
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("employeeId", token.getUid());
			param.put("isDeleted", 0);
			count = employeeUserInfoService.queryByParamCount(param);
			MEMBER_ACCESS_MAP.put(token.getUid(), count);
		}
		if(count==0){
			log.error("[验证Token error] = {}",MResultInfo.USER_AUTH_NULL.message);
			throw new MobileException(MResultInfo.USER_AUTH_NULL);	
		}
		return token;
	}


	static class CacheMap<K, V> extends AbstractMap<K, V> {
		private static final long DEFAULT_TIMEOUT = 30000;
		private static CacheMap<Object, Object> defaultInstance;

		public static synchronized final CacheMap<Object, Object> getDefault() {
			if (defaultInstance == null) {
				defaultInstance = new CacheMap<Object, Object>(DEFAULT_TIMEOUT);
			}
			return defaultInstance;
		}
		private class CacheEntry implements Entry<K, V> {
			long time;
			V value;
			K key;
			CacheEntry(K key, V value) {
				super();
				this.value = value;
				this.key = key;
				this.time = System.currentTimeMillis();
			}
			@Override
			public K getKey() {
				return key;
			}
			@Override
			public V getValue() {
				return value;
			}
			@Override
			public V setValue(V value) {
				return this.value = value;
			}
		}
		private class ClearThread extends Thread {
			ClearThread() {
				setName("clear cache thread");
			}
			public void run() {
				while (true) {
					try {
						long now = System.currentTimeMillis();
						Object[] keys = map.keySet().toArray();
						for (Object key : keys) {
							CacheEntry entry = map.get(key);
							if (now - entry.time >= cacheTimeout) {
								synchronized (map) {
									map.remove(key);
									log.error("缓存KEY:"+key+"已移除！");
								}
							}
						}
						Thread.sleep(10000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		private long cacheTimeout;
		private Map<K, CacheEntry> map = new HashMap<K, CacheEntry>();
		public CacheMap(long timeout) {
			this.cacheTimeout = timeout;
			new ClearThread().start();
		}
		@Override
		public synchronized Set<Entry<K, V>> entrySet() {
			Set<Entry<K, V>> entrySet = new HashSet<Map.Entry<K, V>>();
			Set<Entry<K, CacheEntry>> wrapEntrySet = map.entrySet();
			for (Entry<K, CacheEntry> entry : wrapEntrySet) {
				entrySet.add(entry.getValue());
			}
			return entrySet;
		}
		@Override
		public synchronized V get(Object key) {
			CacheEntry entry = map.get(key);
			return entry == null ? null : entry.value;
		}
		@Override
		public synchronized V put(K key, V value) {
			CacheEntry entry = new CacheEntry(key, value);
			synchronized (map) {
				map.put(key, entry);
				log.error("KEY:"+key+"value:"+value+"----------创建完成");
			}
			return value;
		}
		@Override
		public synchronized V remove(Object key) {
			CacheEntry entry = map.get(key);
			return (V) map.remove(key);
		}
	}
}
			 