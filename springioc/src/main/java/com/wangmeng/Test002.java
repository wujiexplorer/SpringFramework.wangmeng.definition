package com.wangmeng;

import com.wangmeng.service.UserService;
import com.wangmeng.xml.spring.ExtClassPathXmlApplicationContext;

public class Test002 {

    public static void main(String[] args) throws Exception{
        ExtClassPathXmlApplicationContext extClassPathXmlApplicationContext = new ExtClassPathXmlApplicationContext("spring02.xml");
        UserService userService = (UserService)extClassPathXmlApplicationContext.getBean("userService1");
        userService.add();
    }
}
