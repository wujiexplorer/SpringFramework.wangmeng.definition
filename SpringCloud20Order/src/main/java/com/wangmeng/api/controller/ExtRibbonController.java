package com.wangmeng.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/13
 * TIME 16:57
 * Description no Description
 **/
@RestController
public class ExtRibbonController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    private int reqCount;


    @RequestMapping("/ribbonMember")
    public String ribbonMember(){

        String instancesUrl = getInstances()+"/getMember";
        System.out.println("instancesUrl:"+instancesUrl);
        String result = restTemplate.getForObject(instancesUrl, String.class);
        return result;
    }

    private String getInstances(){
        List<ServiceInstance> instances = discoveryClient.getInstances("app-wangmeng-member");
        if(instances == null || instances.size() <= 0){
            return null;
        }
        int instanceSize = instances.size();
        int serviceIndex = reqCount % instanceSize;
        reqCount++;
        return instances.get(serviceIndex).getUri().toString();
    }


}
