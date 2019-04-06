package com.wangmeng.sql;

import com.wangmeng.orm.mybatis.aop.MyInvocationHandlerMybatis;

import java.lang.reflect.Proxy;

public class SqlSession {

    public static <T> T getMapper(Class clazz){
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},new MyInvocationHandlerMybatis(clazz));
    }//Clazz本身就是借口，没有getInterface(),如果是类的话，就可以的
}
