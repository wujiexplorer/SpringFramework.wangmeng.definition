package com.wangmeng02.test;

public class MainTestClass {

    public static void main(String[] args) throws Exception{
        Class<?> clazz = Class.forName("com.wangmeng02.test.Test");
        Object object = clazz.newInstance();//Class加载后才能实例化
        System.out.println("Class=========>:"+clazz+",Object:======>"+object);
    }
}
