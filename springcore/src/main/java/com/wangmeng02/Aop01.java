package com.wangmeng02;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class Aop01 {

    @Before("execution(* com.wangmeng02.service.UserService.add(..))")
    public void bean(){
        System.out.println("。。。。前置通知。。。。。。");
    }

    @After("execution(* com.wangmeng02.service.UserService.add(..))")
    public void commit(){
        System.out.println("。。。。后置通知。。。。。。");
    }

    @AfterReturning("execution(* com.wangmeng02.service.UserService.add(..))")
    public void afterRun(){
        System.out.println("。。。。运行通知。。。。。。");
    }

    @AfterThrowing("execution(* com.wangmeng02.service.UserService.add(..))")
    public void afterException(){
        System.out.println("。。。。异常通知。。。。。。");
    }

    @Around("execution(* com.wangmeng02.service.UserService.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("。。。。环绕通知。。。。。。前");
        proceedingJoinPoint.proceed();//不执行目标方法，所以后置通知，运行通知，异常通知都不会有
        System.out.println("。。。。环绕通知。。。。。。后");
    }
}
