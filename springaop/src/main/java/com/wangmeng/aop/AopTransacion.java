package com.wangmeng.aop;

import com.wangmeng.transaction.TransactionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Component
//@Aspect
public class AopTransacion {

    @Autowired
    private TransactionUtils transactionUtils;


    @AfterThrowing("execution(* com.wangmeng.service.UserService.add(..))")
    public void afterThrowing(){
        System.out.println("回滚事务。。。。。");
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }


    @Around("execution(* com.wangmeng.service.UserService.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
        System.out.println("开启事务。。。。");
        TransactionStatus transactionStatus = transactionUtils.begin();
        // try{
        proceedingJoinPoint.proceed();
        //     }catch (Throwable throwable){
        //       throwable.printStackTrace();
        //    }加了try catch 环绕通知 调用方法之后执行 可以打印 ，运行通知 异常通知没有
        System.out.println("提交事务。。。。");
        transactionUtils.commit(transactionStatus);
    }
}
