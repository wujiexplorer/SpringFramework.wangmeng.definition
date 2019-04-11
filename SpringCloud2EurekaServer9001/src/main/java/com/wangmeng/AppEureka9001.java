package com.wangmeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/10
 * TIME 22:38
 * Description no Description
 **/
@SpringBootApplication
@EnableEurekaServer
public class AppEureka9001 {

    /**
     * Eureka开始有主从，也就是只有一个节点有服务注册信息，如果
     * 一个主节点宕机后，主从节点都有服务注册信息
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(AppEureka9001.class,args);
    }
}
