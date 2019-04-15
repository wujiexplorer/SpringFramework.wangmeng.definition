package com.wangmeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/14
 * TIME 22:19
 * Description no Description
 **/
@SpringBootApplication
@EnableEurekaClient
public class AppConfigClient {

    public static void main(String[] args) {
        SpringApplication.run(AppConfigClient.class,args);
    }
}
