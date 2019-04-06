package com.wangmeng.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class UserEntity implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, DisposableBean , InitializingBean {

    private String useName;
    private Integer age;

    public UserEntity(){
        System.out.println("无参构造器。。。。。。");
    }

    public UserEntity(String useName,Integer age){
        System.out.println("我是有参构造器，userName:"+useName+",age:"+age);
        this.useName = useName;
        this.age = age;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        System.out.println("setUserName:"+useName);
        this.useName = useName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "useName='" + useName + '\'' +
                ", age=" + age +
                '}';
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("set BeanFactory ");
    }

    public void setBeanName(String name) {
        System.out.println("setBeanName,name:"+name);
    }

    public void destroy() throws Exception {
        System.out.println("销毁bean");
    }



    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化Bean中。。。。");
    }
}
