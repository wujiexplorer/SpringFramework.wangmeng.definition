package com.wangmeng.api.controller;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/11
 * TIME 8:56
 * Description no Description
 **/
@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/getOrder")
    public String getOrder(){
        String url = "http://app-wangmeng-member/getMember";
        String result =restTemplate.getForObject(url,String.class);
        System.out.println("订单服务调用会员服务result:"+result);
        return result;
    }
}
