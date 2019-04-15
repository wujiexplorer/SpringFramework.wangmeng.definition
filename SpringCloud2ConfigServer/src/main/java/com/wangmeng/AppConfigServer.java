package com.wangmeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/14
 * TIME 21:41
 * Description no Description
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class AppConfigServer {

    public static void main(String[] args) {
        SpringApplication.run(AppConfigServer.class,args);
    }
}
