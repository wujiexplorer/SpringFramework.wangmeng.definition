package com.wangmeng.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Dog implements ApplicationContextAware {

    private ApplicationContext applicationContext;

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

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
