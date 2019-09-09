package com.lx.benefits.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "yian")
@Lazy(false)
@Data
public class YiAn {

    private String apiHost;

    private String clientId;

    private String clientSecret;
}
