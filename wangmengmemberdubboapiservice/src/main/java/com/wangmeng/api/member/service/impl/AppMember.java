package com.wangmeng.api.member.service.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/17
 * TIME 21:52
 * Description no Description
 **/
public class AppMember {

    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("dubbo-provider.xml");
        app.start();
        System.out.println("会员服务启动成功。。。。");
        System.in.read();//保持服务一直在运行
    }
}
