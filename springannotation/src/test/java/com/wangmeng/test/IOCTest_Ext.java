package com.wangmeng.test;

import com.wangmeng.aop.MathCalculator;
import com.wangmeng.config.MainConfigOfAOP;
import com.wangmeng.ext.ExtConfig;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_Ext {

    @Test
    public void test01(){
        //创建IOC容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);
        applicationContext.publishEvent(new ApplicationEvent(new String("我发布的事件")){

        });
        //for (final ApplicationListener<?> listener : getApplicationListeners(event, type))
        //invokeListener(listener, event);
        //每个listener执行之前是有条件的，if(event==this.getSource.event)
        applicationContext.close();
    }
}
