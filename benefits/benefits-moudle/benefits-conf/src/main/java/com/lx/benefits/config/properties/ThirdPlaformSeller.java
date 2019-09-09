package com.lx.benefits.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "third.platform.seller")
@Lazy(false)
@Data
public class ThirdPlaformSeller {
    /**
     * jd商家信息
     */
    private Seller jd = new Seller();

    private Seller yx = new Seller();


    @Data
    public class Seller {
        private Long id;
        private String name;
    }
}
