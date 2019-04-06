package com.wangmeng.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
//@Aspect
public class AopLog {

    @Before("execution(* com.wangmeng.service.UserService.add(..))")
    public void before(){
        System.out.println("前置通知 在方法之前执行。。。");
    }

    @After("execution(* com.wangmeng.service.UserService.add(..))")
    public void after(){
        System.out.println("后置通知 在方法之后执行。。。。");
    }

    @AfterReturning("execution(* com.wangmeng.service.UserService.add(..))")
    public void returning(){
        System.out.println("运行通知。。。。。");
    }

    @AfterThrowing("execution(* com.wangmeng.service.UserService.add(..))")
    public void afterThrowing(){
        System.out.println("异常通知。。。。。");
    }

    @Around("execution(* com.wangmeng.service.UserService.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
        System.out.println("环绕通知 调用方法之前执行。。。。");
       // try{
            proceedingJoinPoint.proceed();
   //     }catch (Throwable throwable){
     //       throwable.printStackTrace();
    //    }加了try catch 环绕通知 调用方法之后执行 可以打印 ，运行通知 异常通知没有
        System.out.println("环绕通知 调用方法之后执行。。。。");
    }
}
