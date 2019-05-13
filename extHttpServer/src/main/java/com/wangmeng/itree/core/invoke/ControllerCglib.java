package com.wangmeng.itree.core.invoke;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author linhao
 * @date 2019/4/28
 * @Version V1.0
 */
public class ControllerCglib implements MethodInterceptor {

    private Object target;

    public Object getTarget(Object target){
        this.target=target;
        Enhancer enhancer=new Enhancer();
        //设置当前的代理对象
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        preHandle();
        Object value=methodProxy.invokeSuper(o,objects);
        afterHandle();
        return value;
    }


    private void preHandle(){
    }

    private void afterHandle(){

    }

    public static void main(String[] args) {

    }
}
