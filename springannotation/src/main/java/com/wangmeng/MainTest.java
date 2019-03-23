package com.wangmeng;

import com.wangmeng.bean.Person;
import com.wangmeng.config.MainConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {
    public static void main(String[] args){
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
//        Person person = (Person)applicationContext.getBean("person");
//        System.out.println(person);
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
//        Person bean = applicationContext.getBean(Person.class);
//        System.out.println(bean);
//        String[] namesForType = applicationContext.getBeanNamesForType(Person.class);
//        for(String name : namesForType){
//            System.out.println(name);
//        }
        Integer flag = 10000;
        if(flag ==10000){
            System.out.println(flag);
        }
    }
}
