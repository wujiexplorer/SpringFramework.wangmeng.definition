package com.wangmeng.api.controller;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/10
 * TIME 23:03
 * Description no Description
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2Doc
public class AppMember {

    public static void main(String[] args) {
        SpringApplication.run(AppMember.class,args);
    }
}
