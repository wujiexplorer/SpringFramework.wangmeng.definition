package com.wangmeng.test;

import com.wangmeng.arraylist.ExtArrayList;
import com.wangmeng.arraylist.JDKArrayList;

import java.util.ArrayList;

public class Test002 {

    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("张是哪");
        JDKArrayList<String> jdkArrayList = new JDKArrayList<>();
        jdkArrayList.add("wangmeng");
        System.out.println(jdkArrayList);
        ExtArrayList extArrayList =new ExtArrayList(1);
        extArrayList.add("张三");
        extArrayList.add("李四");
        extArrayList.add("王五");
        System.out.println(extArrayList.get(0));
        int oldCapacity = 3;
        //1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        System.out.println(newCapacity);
    }
}
