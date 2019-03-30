package com.wangmeng.controller;


import com.wangmeng.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
       String hello = helloService.sayHello("tomcat....");
        return "success:"+hello;
    }

    @RequestMapping("/suc")
    public String success(){
        return "success";
    }
}
