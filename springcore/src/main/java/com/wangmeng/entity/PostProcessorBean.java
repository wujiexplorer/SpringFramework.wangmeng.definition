package com.wangmeng.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

public class PostProcessorBean implements BeanPostProcessor {//BeanPostProcessor要单独写出来实现，否则没有效果
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("beanName:"+beanName+"初始化开始");
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("beanName:"+beanName+"初始化结束");
        return bean;
    }
}
