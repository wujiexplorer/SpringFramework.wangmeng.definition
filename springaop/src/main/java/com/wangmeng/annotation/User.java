package com.wangmeng.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.reflect.Method;


public class User {

    @AddAnnotation(userId = 19,userName = "张三",arrays = {"1"})
    public void add(){

    }

    public void del(){

    }

    public static void main(String[] args)throws Exception{
        Class<?> clazz = Class.forName("com.wangmeng.annotation.User");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for(Method method : declaredMethods){
            System.out.println("##############方法名称："+method.getName());
            AddAnnotation addAnnotation = method.getDeclaredAnnotation(AddAnnotation.class);
            if(addAnnotation == null){
                System.out.println("该方法没有加该注解。。。。。");
                System.out.println();
                continue;
            }
            System.out.println("userId:"+addAnnotation.userId());
            System.out.println("userName:"+addAnnotation.userName());
            System.out.println("arrays:"+addAnnotation.arrays());
            System.out.println();
        }
    }
}
