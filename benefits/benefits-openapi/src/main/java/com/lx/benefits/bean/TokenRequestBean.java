package com.lx.benefits.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import lombok.Data;

@Data
public class TokenRequestBean {

	@NotEmpty(message = "clientId不能为空")
	private String clientId;
	@NotEmpty(message = "clientSecret不能为空")
	private String clientSecret;
	@NotEmpty(message = "grantType不能为空")
	private String grantType;
	@NotEmpty(message = "timestamp不能为空")
	private String timestamp;
	@NotEmpty(message = "签名不能为空")
	private String sign;

	public boolean signCheck() {
		if (StringUtils.isEmpty(timestamp) || !StringUtils.isEmpty(sign)) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date parse;
			try {
				parse = simpleDateFormat.parse(timestamp);
			} catch (ParseException e) {
				return false;
			}
			if (Math.abs(parse.getTime() - System.currentTimeMillis()) < 30 * 60 * 1000l) {
				return sign.equalsIgnoreCase(
						DigestUtils.md5DigestAsHex(String.format("%s%s%s%s%s", clientSecret, timestamp, clientId, "access_token", clientSecret).getBytes()));
			}
		}
		return false;
	}
}
