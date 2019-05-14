package com.wangmeng.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User:wangmeng
 * Date:2019/5/14
 * Time:10:17
 * Verision:2.x
 * Description:TODO
 **/

@Configuration
@EnableConfigurationProperties(HelloProperties.class)
public class HelloAuto {

    @Autowired
    HelloProperties helloProperties;

    @Bean
    public HelloService helloService() {
        HelloService helloService = new HelloService();
        helloService.setHelloProperties(helloProperties);
        return helloService;
    }

}
