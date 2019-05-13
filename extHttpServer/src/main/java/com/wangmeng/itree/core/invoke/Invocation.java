package com.wangmeng.itree.core.invoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 该类已过时，不建议使用
 *
 * @author idea
 * @data 2019/4/27
 */
@Deprecated
public class Invocation implements InvocationHandler {

    /**
     * 目标对象
     */
    private Object target;

    public Invocation(Object target) {
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }

    /**
     * 获取目标对象的代理对象
     *
     * @return 代理对象
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass().getInterfaces(), this);
    }


}
