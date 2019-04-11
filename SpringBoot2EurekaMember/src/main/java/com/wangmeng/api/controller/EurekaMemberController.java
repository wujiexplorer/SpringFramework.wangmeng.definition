package com.wangmeng.api.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/11
 * TIME 22:15
 * Description no Description
 **/
@RestController
@SpringBootApplication
@EnableEurekaClient
public class EurekaMemberController {

    @RequestMapping("/getMember")
    public String getMember(){

        return "会员服务，订单服务调用会员服务接口";
    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaMemberController.class,args);
    }
}
