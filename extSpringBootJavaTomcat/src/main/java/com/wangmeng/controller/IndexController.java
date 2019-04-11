package com.wangmeng.controller;

import com.wangmeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/10
 * TIME 12:24
 * Description no Description
 **/
@RestController
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index",produces = "text/html;charset=UTF-8")
    public String index(){
        System.out.println(userService.index());
        return "纯手写Spring Boot";
    }
}
