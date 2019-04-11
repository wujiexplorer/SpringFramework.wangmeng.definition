package com.wangmeng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/10
 * TIME 12:45
 * Description no Description
 **/
@Controller
public class UserController {

    /**
     *war包，自己写的Spring Boot由于权限问题无法访问webapp下的静态资源
     */
    @RequestMapping("/pageIndex")
    public String pageIndex(){
        return "pageIndex";
    }
}
