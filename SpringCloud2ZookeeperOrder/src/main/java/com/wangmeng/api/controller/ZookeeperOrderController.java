package com.wangmeng.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/11
 * TIME 22:28
 * Description no Description
 **/
@SpringBootApplication
@EnableDiscoveryClient
@RestController//没有加@RestController，会报404错误
public class ZookeeperOrderController {

    /**
     * 删除Zookeeper节点后，30秒内可以访问，之后不能访问
     */

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/orderToMember")
    public String orderToMember(){
        String memberUrl = "http://zk-member/getMember";
        return restTemplate.getForObject(memberUrl,String.class);
       // return null;
    }

    @RequestMapping("/discoveryClientMember")
    public List<ServiceInstance> getDiscoveryClientMember(){
        List<ServiceInstance> instances = discoveryClient.getInstances("zk-member");
        for(ServiceInstance serviceInstance : instances){
            System.out.println("url:"+serviceInstance.getUri());
        }
        return instances;

    }

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperOrderController.class,args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
