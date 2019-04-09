package com.wangmeng.test;

import com.wangmeng.ext.hashmap.ExtHashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/9
 * TIME 10:14
 * Description no Description
 **/
public class Test002 {

    public static void main(String[] args) {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("a","aaa");

        // 基于什么原则 后进选出 非公平锁与公平锁
        ExtHashMap<String,String> extHashMap = new ExtHashMap<String, String>();
        extHashMap.put("1号", "1号");// 0
        extHashMap.put("2号", "1号");// 1
        extHashMap.put("3号", "1号");// 2
        extHashMap.put("4号", "1号");// 3
        extHashMap.put("6号", "1号");// 4
        extHashMap.put("7号", "1号");
        //extHashMap.print();
        extHashMap.put("14号", "1号");
        //extHashMap.put("15号", "1号");
       // extHashMap.print();
        extHashMap.put("3号", "ccccc");
        System.out.println("修改3号之后");
        extHashMap.print();
        System.out.println(extHashMap.get("14号"));

        extHashMap.put("22号", "1号");
        extHashMap.put("26号", "1号");
        extHashMap.put("27号", "1号");
        extHashMap.put("28号", "1号");
        extHashMap.put("66号", "66");
        extHashMap.put("30号", "1号");
        System.out.println("扩容前数据....");
        extHashMap.print();
        System.out.println("扩容后数据....");
        extHashMap.put("31号", "1号");
        extHashMap.put("66号", "王猛");
        extHashMap.print();
        // 修改3号之后
        System.out.println(extHashMap.get("66号"));
    }
}
