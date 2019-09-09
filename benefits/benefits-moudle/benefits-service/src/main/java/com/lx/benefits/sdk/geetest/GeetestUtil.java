package com.lx.benefits.sdk.geetest;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

//@Component
@Slf4j
public class GeetestUtil {

	@Value("${geetest.id:59106b036115d29e34d473fdd331d844}")
	private String geetest_id;
	@Value("${geetest.key:542c3455c0f7c661c6db135b7807feb7}")
	private String geetest_key;
	protected final String registerUrl = "http://api.geetest.com/register.php?gt={gt}&json_format=1";
	protected final String validateUrl = "http://api.geetest.com/validate.php";

	@Resource(name = "formStringJsonRestTemplate")
	private RestTemplate restTemplate;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	private static final String FAILE_REGISTER = "geetest_failed_%s";

	public GeetestResult register() {
		try {
			String result_str = restTemplate.getForObject(registerUrl, String.class, geetest_id);
			if (result_str == "fail") {
				return new GeetestResult(false);
			}
			String challenge = JSONObject.parseObject(result_str).getString("challenge");
			log.info("register geetest, result={}", challenge);
			if (challenge.length() == 32) {
				String returnChallenge = DigestUtils.md5Hex(challenge + geetest_key);
				return new GeetestResult(true, returnChallenge, geetest_id);
			}
		} catch (Exception e) {
			log.error("register geetest failed", e);
		}
		String challenge = UUID.randomUUID().toString().replace("-", "");
		stringRedisTemplate.opsForValue().set(String.format(FAILE_REGISTER, challenge), "failed", 30, TimeUnit.MINUTES);
		return new GeetestResult(false, challenge, geetest_id);
	}

	public GeetestResult validate(String challenge, String validate, String seccode) {
		if (StringUtils.isEmpty(challenge) || StringUtils.isEmpty(validate) || StringUtils.isEmpty(seccode)) {
			return new GeetestResult(false);
		}
		try {
			String encodeStr = DigestUtils.md5Hex(geetest_key + "geetest" + challenge);
			if (!validate.equals(encodeStr)) {
				return new GeetestResult(false);
			}
			MultiValueMap<String, Object> request = new LinkedMultiValueMap<>();
			request.set("challenge", challenge);
			request.set("validate", validate);
			request.set("seccode", seccode);
			request.set("json_format", 1);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(request, httpHeaders);
			String result_str = restTemplate.postForObject(validateUrl, httpEntity, String.class);
			log.info("register validate, result={}", result_str);
			String return_seccode = JSONObject.parseObject(result_str).getString("seccode");
			if (return_seccode.equals(DigestUtils.md5Hex(seccode))) {
				return new GeetestResult(true);
			}
		} catch (Exception e) {
			log.error("validate geetest failed", e);
		}
		if (stringRedisTemplate.delete(String.format(FAILE_REGISTER, challenge))) {
			return new GeetestResult(true);
		}
		return new GeetestResult(false);
	}
}
