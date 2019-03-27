package com.wangmeng.test;

import com.wangmeng.aop.MathCalculator;
import com.wangmeng.config.MainConfigOfAOP;
import com.wangmeng.tx.TxConfig;
import com.wangmeng.tx.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_Tx {

    @Test
    public void test01(){
        //创建IOC容器
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(TxConfig.class);
        UserService userService = applicationContext.getBean(UserService.class);
        userService.insertUser();
        applicationContext.close();
    }
}
