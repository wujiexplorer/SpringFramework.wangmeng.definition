package com.wangmeng.aop;


import com.wangmeng.annotation.ExtTransaciton;
import com.wangmeng.transaction.TransactionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.lang.reflect.Method;

@Aspect
@Component
public class AopExtTransaction {

    @Autowired
    private TransactionUtils transactionUtils;

    private  ExtTransaciton extTransaciton;


    @AfterThrowing("execution(* com.wangmeng.service.*.*.*(..))")
    public void afterThrowing(){
        System.out.println("异常通知。。。。。");
        //ExtTransaciton extTransaciton =getMethodExtTransaction(proceedingJoinPoint);
       // if(extTransaciton != null)
        if(extTransaciton != null){
            System.out.println("回滚事务。。。。。");
            transactionUtils.rollback();
        }

        //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();回滚原始注解的事务

    }

    @Around("execution(* com.wangmeng.service.*.*.*(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
       ExtTransaciton extTransaciton = getMethodExtTransaction(proceedingJoinPoint);
       TransactionStatus transactionStatus = begin(extTransaciton);
       proceedingJoinPoint.proceed();
       commit(transactionStatus);
    }

    private TransactionStatus begin(ExtTransaciton extTransaciton){
        if(extTransaciton == null){
            return null;
        }
        return transactionUtils.begin();
    }

    private void commit(TransactionStatus transactionStatus){
        if(transactionStatus != null){
            transactionUtils.commit(transactionStatus);
        }
    }

    private ExtTransaciton getMethodExtTransaction(ProceedingJoinPoint proceedingJoinPoint)throws Exception{
        String methodName = proceedingJoinPoint.getSignature().getName();
        Class<?> classTarget = proceedingJoinPoint.getTarget().getClass();
        Class<?>[] par = ((MethodSignature)proceedingJoinPoint.getSignature()).getParameterTypes();
        Method method = classTarget.getMethod(methodName,par);
       extTransaciton = method.getDeclaredAnnotation(ExtTransaciton.class);
        return extTransaciton;
    }
}
