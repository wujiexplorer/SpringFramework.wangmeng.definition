package com.wangmeng;

import com.wangmeng.service.UserService;
import com.wangmeng.ext.springmvc.ExtClassPathXmlApplicationContext;

public class Test004 {

    public static void main(String[] args) throws Exception{
        ExtClassPathXmlApplicationContext extClassPathXmlApplicationContext = new ExtClassPathXmlApplicationContext("com.wangmeng.service.impl");
        UserService userService = (UserService)extClassPathXmlApplicationContext.getBean("userServiceImpl");
        userService.add();
        System.out.println(userService);
    }
}
