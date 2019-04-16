package com.wangmeng.api.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/15
 * TIME 19:57
 * Description no Description
 **/
@SpringBootApplication
@EnableEurekaClient
@RestController
public class AppPay {

    @RequestMapping("/")
    public String getPay(){
        return "我是支付服务！";
    }
    public static void main(String[] args) {
        SpringApplication.run(AppPay.class,args);
    }
}
