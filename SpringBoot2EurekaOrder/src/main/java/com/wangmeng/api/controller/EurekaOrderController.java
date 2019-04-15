package com.wangmeng.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/11
 * TIME 22:28
 * Description no Description
 **/
@SpringBootApplication
@EnableEurekaClient
@RestController//没有加@RestController，会报404错误
public class EurekaOrderController {



    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/orderToMember")
    public String orderToMember(){
        String memberUrl = "http://app-wangmeng-member/getMember";
        return restTemplate.getForObject(memberUrl,String.class);
       // return null;
    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaOrderController.class,args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
