package com.wangmeng.api.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/11
 * TIME 9:03
 * Description no Description
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class AppOrder {

    public static void main(String[] args) {
        SpringApplication.run(AppOrder.class,args);
    }

    @Bean
   // @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
