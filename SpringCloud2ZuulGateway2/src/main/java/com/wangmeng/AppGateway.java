package com.wangmeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/15
 * TIME 16:37
 * Description no Description
 **/
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class AppGateway {
    /**负载均衡的代码要一模一样，除了配置文件不同外，尤其是启动类要一样zuul规定的
     * Zuul默认整合了ribbon的负载均衡
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(AppGateway.class,args);
    }

    @RefreshScope
    @ConfigurationProperties("zuul")
    public ZuulProperties zuulProperties(){
        return new ZuulProperties();
    }
}
