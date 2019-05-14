package com.wangmeng.controller;

import com.wangmeng.starter.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User:wangmeng
 * Date:2019/5/14
 * Time:10:27
 * Verision:2.x
 * Description:TODO
 **/
@RestController
public class Controller {

    @Autowired
    HelloService helloService;

    @GetMapping("hello")
    public String hello(){
        return helloService.hello();
    }

}
