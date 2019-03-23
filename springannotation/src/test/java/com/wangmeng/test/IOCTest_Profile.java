package com.wangmeng.test;

import com.wangmeng.bean.Boss;
import com.wangmeng.bean.Car;
import com.wangmeng.bean.Color;
import com.wangmeng.bean.Yellow;
import com.wangmeng.config.MainConfigOfAutowired;
import com.wangmeng.config.MainConfigOfProfile;
import com.wangmeng.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class IOCTest_Profile {

    @Test
    public void test01(){
        //创建IOC容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //代码设置的环境会覆盖IDE设置的环境
        applicationContext.getEnvironment().setActiveProfiles("dev");
        applicationContext.register(MainConfigOfProfile.class);
        applicationContext.refresh();
        String[] names = applicationContext.getBeanNamesForType(DataSource.class);
        for(String name : names){
            System.out.println(name);
        }
        Yellow yellow = applicationContext.getBean(Yellow.class);
        System.out.println(yellow);
        applicationContext.close();
    }
}
