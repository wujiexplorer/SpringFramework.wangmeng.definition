package com.wangmeng.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/9
 * TIME 21:26
 * Description no Description
 **/
@RestController
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "SpringBoot 2.0";
    }
}
