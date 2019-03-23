package com.wangmeng.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect//切面类
public class LogAspects {

    @Pointcut("execution(public int com.wangmeng.aop.MathCalculator.*(..))")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println(""+joinPoint.getSignature().getName()+"运行。。。。 @Before..参数列表是：{"+ Arrays.asList(args)+"}");
    }

    @After("com.wangmeng.aop.LogAspects.pointCut()")
    public void logEnd(JoinPoint joinPoint){
        System.out.println(""+joinPoint.getSignature().getName()+"结束。。。。@After......");
    }

    //JoinPoint一定不能放在后面，否则Initialization of bean failed; nested exception is java.lang.IllegalArgumentException: error at ::0 formal unbound in pointcut
    @AfterReturning(value = "pointCut()",returning = "result")
    public void logReturn(JoinPoint joinPoint,Object result){
        System.out.println(""+joinPoint.getSignature().getName()+"正常返回。。。。@AfterReturning:运行结果是：{"+result+"}");
    }

    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void logExcepiton(JoinPoint joinPoint,Exception exception){
        System.out.println(""+joinPoint.getSignature().getName()+"异常。。。。异常信息是：{"+exception+"}");
    }
}
