package com.lx.benefits.web.support;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apollo.common.exception.BusinessException;
import com.lx.benefits.bean.dto.wechat.Code2SessionDTO;
import com.lx.benefits.bean.util.HttpClientUtil;
import com.lx.benefits.config.properties.WXProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WeChatSupport implements InitializingBean{
    @Resource
    private WXProperties wxProperties;

    @Resource(name = "httpClient")
    private CloseableHttpClient httpClient;
    @Resource(name = "formStringJsonRestTemplate")
	public RestTemplate restTemplate;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	private final String WX_ACCESS_TOKEN_CACH_KEY = "wx_access_token";
	private String accessTokenUrlFormat;
	
    public Code2SessionDTO code2Session(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        Map<String, Object> params = new HashMap<>(4);
        params.put("appid", wxProperties.getAppId());
        params.put("secret", wxProperties.getAppSecret());
        params.put("js_code", code);
        params.put("grant_type", "authorization_code");
        String result;
        try {
            result = HttpClientUtil.httpGetRequest(wxProperties.getJsCode2SessionUrl(), params,httpClient);
        }
        catch (URISyntaxException e) {
            log.error("根据前端CODE获取用户信息出错,{}", e.getMessage());
            return null;
        }
        Code2SessionDTO code2SessionDTO = JSON.parseObject(result, Code2SessionDTO.class);
        if (StringUtils.isNotEmpty(code2SessionDTO.getErrCode())) {
            log.error("根据CODE获取用户信息接口出错,result:{}", result);
            return null;
        }
        return code2SessionDTO;
    }

	@Override
	public void afterPropertiesSet() throws Exception {
		accessTokenUrlFormat = wxProperties.getAccessTokenUrl() + "?grant_type=client_credential&appid={appid}&secret={secret}";
	}


	// 获取微信授权token值
	public String getAccessToken(boolean flushcache) {
		ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
		String access_token = null;
		if (flushcache) {
			stringRedisTemplate.delete(WX_ACCESS_TOKEN_CACH_KEY);
			log.info("flushcache wx AccessToken, cacheKey={}", WX_ACCESS_TOKEN_CACH_KEY);
		} else {
			access_token = opsForValue.get(WX_ACCESS_TOKEN_CACH_KEY);
		}
		if (StringUtils.isEmpty(access_token)) {
			synchronized (WeChatSupport.class) {
				access_token = opsForValue.get(WX_ACCESS_TOKEN_CACH_KEY);
				if (StringUtils.isEmpty(access_token)) {
					JSONObject response = restTemplate.getForObject(accessTokenUrlFormat, JSONObject.class, wxProperties.getAppId(),
							wxProperties.getAppSecret());
					access_token = response.getString("access_token");
					if (!StringUtils.isEmpty(access_token)) {
						int expires_in = response.getIntValue("expires_in");
						opsForValue.set("wx_access_token", access_token, expires_in, TimeUnit.SECONDS);
					}
				}
			}
		}
		return access_token;
	}

	public ResponseEntity<org.springframework.core.io.Resource> getWxaCode(String scene,boolean isYian) {
		if (StringUtils.isEmpty(scene) || scene.length() > 32) {
			throw new BusinessException("scene不合法");
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("scene", scene);
		if(isYian) {
			jsonObject.put("page", wxProperties.getIndexPage());
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<JSONObject> httpEntity = new HttpEntity<>(jsonObject, headers);
		ResponseEntity<org.springframework.core.io.Resource> entity = restTemplate.postForEntity(
				"https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token={access_token}", httpEntity, org.springframework.core.io.Resource.class,
				this.getAccessToken(false));
		if (entity.getHeaders().getContentType().includes(MediaType.APPLICATION_JSON)) {
			String result = "";
			try {
				result = IOUtils.toString(entity.getBody().getInputStream());
				log.error("getWxaCode, result={}", result);
			} catch (IOException e) {
			}
			if (result.contains("40001")) {// token过期
				entity = restTemplate.postForEntity("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token={access_token}", httpEntity,
						org.springframework.core.io.Resource.class, this.getAccessToken(true));
			}
		}
		return entity;
	}
}
