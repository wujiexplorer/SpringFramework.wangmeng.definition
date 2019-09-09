package com.lx.benefits.bean.util;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * User: fan
 * Date: 2019/03/24
 * Time: 12:34
 */
public class RetryUtil {

    private static ThreadLocal<DelayRetry> retryTimesInThread = new ThreadLocal<>();


    /**
     * 设置当前方法延时重试次数
     * @param retryTimes
     * @param delay
     * @return
     */
    public static RetryUtil setDelayRetryTimes(Integer retryTimes, Integer delay) {
        if (retryTimesInThread.get() == null) {
            retryTimesInThread.set(new DelayRetry(retryTimes, delay));
        }
        return new RetryUtil();
    }

    /**
     * 设置当前方法重试次数
     *
     * @param retryTimes
     * @return
     */
    public static RetryUtil setRetryTimes(Integer retryTimes) {
        if (retryTimesInThread.get() == null) {
            retryTimesInThread.set(new DelayRetry(retryTimes));
        }
        return new RetryUtil();
    }

    /**
     * 重试当前方法
     * <p>按顺序传入调用者方法的所有参数</p>
     *
     * @param args
     * @return
     */
    public Object retry(Object... args) {
        try {
            DelayRetry delayRetry = retryTimesInThread.get();
            if (delayRetry == null || delayRetry.getRetryTimes() <= 0) {
                retryTimesInThread.remove();
                return null;
            }
            delayRetry.setRetryTimes((delayRetry.getRetryTimes() - 1));
            retryTimesInThread.set(delayRetry);
            String upperClassName = Thread.currentThread().getStackTrace()[2].getClassName();
            String upperMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();

            Class clazz = Class.forName(upperClassName);
            Object targetObject = clazz.newInstance();
            Method targetMethod = null;
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.getName().equals(upperMethodName)) {
                    targetMethod = method;
                    break;
                }
            }
            if (targetMethod == null) {
                return null;
            }
            targetMethod.setAccessible(true);
            if (delayRetry.getDelay() > 0) {
                Thread.sleep(delayRetry.getDelay());
            }
            return targetMethod.invoke(targetObject, args);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Data
    public static class DelayRetry {

        Integer retryTimes;

        Integer delay = 0;

        public DelayRetry(Integer retryTimes) {
            this.retryTimes = retryTimes;
        }

        public DelayRetry(Integer retryTimes, Integer delay) {
            this.retryTimes = retryTimes;
            this.delay = delay;
        }
    }

}
