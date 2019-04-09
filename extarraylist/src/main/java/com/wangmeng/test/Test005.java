package com.wangmeng.test;

import com.wangmeng.arraylist.ExtSystem;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/9
 * TIME 8:55
 * Description no Description
 **/
public class Test005 {

    public static void main(String[] args) {
        //加载动态库
        //System. loadLibrary ("TestJNI");
        //System.loadLibrary("jli");
        ExtSystem extSystem = new ExtSystem();
        //native static 实体类不能调用
        System.out.println(ExtSystem.currentTimeMillis());
        System.out.println(ExtSystem.nanoTime());
        System.out.println(extSystem.hashCode());
    }
}
