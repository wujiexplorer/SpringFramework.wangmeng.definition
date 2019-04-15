package com.wangmeng.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
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
@EnableDiscoveryClient
public class ZookeeperMemberController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/getMember")
    public String getMember(){

        return "会员服务，订单服务调用会员服务接口,端口号："+serverPort;
    }

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperMemberController.class,args);
    }
}
