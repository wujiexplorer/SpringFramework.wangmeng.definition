package com.wangmeng.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Dog {

    public Dog(){
        System.out.println("Dog .....Constructor.....");
    }

    //创建Bean并属性设置完成，初始化
    @PostConstruct
    public void init(){
        System.out.println("Dog.... @PostConstruct.....");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Dog.... @PreDestroy......");
    }
}
