package com.wangmeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/14
 * TIME 15:41
 * Description no Description
 **/
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class AppMember2 {

    public static void main(String[] args) {
        SpringApplication.run(AppMember2.class,args);
    }
}
