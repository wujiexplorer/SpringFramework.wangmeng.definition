package com.wangmeng.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Cat implements InitializingBean, DisposableBean {

    public Cat(){
        System.out.println("cat....constructor...");
    }


    public void destroy() throws Exception {
        System.out.println("cat....destroy...");
    }
    //创建Bean完成，Bean的属性也设置值了
    public void afterPropertiesSet() throws Exception {
        System.out.println("cat...afterPropertiesSet...");
    }
}
