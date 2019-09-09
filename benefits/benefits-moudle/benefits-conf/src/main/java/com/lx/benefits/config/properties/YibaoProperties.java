package com.lx.benefits.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "yibao")
@Data
public class YibaoProperties {

	private String apiUrl;
	private String appId;
	private String appKey;
	private String platformId;
	private String orgCode;
	private Long enterprId;// 易豹对应的企业ID
}
