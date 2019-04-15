package com.wangmeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/13
 * TIME 23:28
 * Description no Description
 **/
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class AppMember {

    public static void main(String[] args) {
        SpringApplication.run(AppMember.class,args);
    }
}
