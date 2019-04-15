package com.wangmeng.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/14
 * TIME 22:13
 * Description no Description
 **/

/**
 * /actuator/refresh要配合@RefreshScope才能手动刷新
 * 实际生产环境一般都采用手动刷新，可以把所有的配置放在一个类中
 * 并加上@RefreshScope
 */
@RestController
@RefreshScope
public class TestController {

    @Value("${wangmengInfo}")
    private String wangmengInfo;

    @RequestMapping("/getWangmengInfo")
    public String getWangmengInfo(){
        return wangmengInfo;
    }
}
