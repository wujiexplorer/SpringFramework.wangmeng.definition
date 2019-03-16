package com.wangmeng.test;

import com.wangmeng.config.MainConfig;
import com.wangmeng.config.MainConfig2;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest {

    @Test
    public void test01(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext
                =new AnnotationConfigApplicationContext(MainConfig.class);
        String[] definitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for(String name : definitionNames){
            System.out.println(name);
        }
    }

    @Test
    public void test02(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext
                =new AnnotationConfigApplicationContext(MainConfig2.class);
//        String[] definitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
//        for(String name : definitionNames){
//            System.out.println(name);
//        }
//        //默认是单实例
        System.out.println("ioc容器创建完成.....");
        Object bean = annotationConfigApplicationContext.getBean("person");
        Object bean2 = annotationConfigApplicationContext.getBean("person");
//        Object bean2 = annotationConfigApplicationContext.getBean("person");
        System.out.println(bean == bean2);//true
    }

}
