package com.wangmeng.test;

import com.wangmeng.bean.Blue;
import com.wangmeng.bean.Person;
import com.wangmeng.config.MainConfig;
import com.wangmeng.config.MainConfig2;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

public class IOCTest {

    AnnotationConfigApplicationContext annotationConfigApplicationContext
            =new AnnotationConfigApplicationContext(MainConfig2.class);

    @Test
    public void testImport(){
        printBeans(annotationConfigApplicationContext);
        Blue bean  = annotationConfigApplicationContext.getBean(Blue.class);
        System.out.println(bean);
        //工厂Bean获取的是调用getObject创建的对象
        Object bean2 = annotationConfigApplicationContext.getBean("colorFactoryBean");
        Object bean3 = annotationConfigApplicationContext.getBean("colorFactoryBean");
        System.out.println("bean的类型："+bean2.getClass());
        System.out.println(bean2 == bean3);
        Object bean4 = annotationConfigApplicationContext.getBean("&colorFactoryBean");
        System.out.println(bean4.getClass());
    }

    private void printBeans(AnnotationConfigApplicationContext annotationConfigApplicationContext){
        String[] definitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for(String name : definitionNames){
            System.out.println(name);
        }
    }


    @Test
    public void test03(){
        String[] namesForType = annotationConfigApplicationContext.getBeanNamesForType(Person.class);
        ConfigurableEnvironment environment = annotationConfigApplicationContext.getEnvironment();
        //动态获取环境变量的值：Window 7
        String property = environment.getProperty("os.name");
        System.out.println(property);
        for(String name : namesForType){
            System.out.println(name);
        }
        Map<String ,Person> persons = annotationConfigApplicationContext.getBeansOfType(Person.class);
        System.out.println(persons);
    }

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
