package com.lx.benefits.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "wx")
@Component
@Data
public class WXProperties {
    private String appId;

    private String appSecret;

    private String jsCode2SessionUrl;

    private String defaultAvatar;
    
	private String wxaCodeUrl;
	
	private String accessTokenUrl;
	
	private String indexPage;
}
