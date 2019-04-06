package com.wangmeng.controller;

import com.wangmeng.ext.springmvc.extannotation.ExtController;
import com.wangmeng.ext.springmvc.extannotation.ExtRequestMapping;

@ExtController
@ExtRequestMapping("/ext")
public class ExtIndexController {

    @ExtRequestMapping("/test")
    public String test(){
        System.out.println("手写Springmvc框架。。。。。");
        return "index";
    }
}
