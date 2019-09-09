package com.lx.benefits.config.properties;

import lombok.Data;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
@ConfigurationProperties(prefix = "yx")
@Lazy(false)
@Data
public class YanXuan implements InitializingBean {

    private String apiHost;

    private String apiUrl;

    private String apiKey;

    private String apiSecret;
    private String requestUrl;
    
    @Override
	public void afterPropertiesSet() throws Exception {
		if (StringUtils.isEmpty(requestUrl)) {
			requestUrl = (apiHost + "/" + apiUrl).replaceAll("(?<!:)/{2,}", "/");
		}
	}
}
