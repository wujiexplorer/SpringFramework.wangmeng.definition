package com.wangmeng.test;

import com.wangmeng.bean.Person;
import com.wangmeng.config.MainConfigOfLifeCycle;
import com.wangmeng.config.MainConfigOfPropertyValue;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class IOCTest_PropertyValues {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertyValue.class);

    @Test
    public void test01() {
        printBeans(applicationContext);
        System.out.println("=============================");
        Person person = (Person)applicationContext.getBean("person");
        System.out.println(person);

        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("person.nickName");
        System.out.println(property);
        applicationContext.close();
    }

    private void printBeans(AnnotationConfigApplicationContext annotationConfigApplicationContext) {
        String[] definitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }
}
