package com.wangmeng02;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;


public class Aop02 {


    @Autowired
    private TransactionUtils transactionUtils;

    private  TransactionStatus transactionStatus;

    public void bean(){
       // System.out.println("。。。。前置通知。。。。。。");
    }


    public void commit(){
        //System.out.println("。。。。后置通知。。。。。。");
    }


    public void afterRun(){
        //System.out.println("。。。。运行通知。。。。。。");
    }


    public void afterException(){
        System.out.println("。。。。异常通知。。。。。。");
        transactionUtils.rollback(transactionStatus);
    }


    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("。。。。环绕通知。。。。。。前");
        transactionStatus = transactionUtils.begin();
        proceedingJoinPoint.proceed();//不执行目标方法，所以后置通知，运行通知，异常通知都不会有
        transactionUtils.commit(transactionStatus);
        System.out.println("。。。。环绕通知。。。。。。后");

    }
}
