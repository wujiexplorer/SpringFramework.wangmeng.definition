package com.wangmeng.test;

import com.wangmeng.ext.hashmap.JdkHashMap;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/8
 * TIME 18:47
 * Description no Description
 **/
public class Test001 {

    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        hashMap.put("a","aaaaaa");
        JdkHashMap jdkHashMap = new JdkHashMap();
        jdkHashMap.put("a","bbbbb");
    }
}
