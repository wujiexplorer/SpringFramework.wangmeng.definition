package com.wangmeng.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/13
 * TIME 21:08
 * Description no Description
 **/
@RestController
public class FeignController {
    /**
     * A component required a bean of type 'com.wangmeng.feign.MemberApiFeign' that could not be found.
     */
    @Autowired
    private MemberApiFeign memberApiFeign;

    @RequestMapping("/feignMember")
    public String feignMember(){
       return memberApiFeign.getMember();
    }
}
