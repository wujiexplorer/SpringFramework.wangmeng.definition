package com.wangmeng.test;

import com.wangmeng.arraylist.ExtArrayList;
import com.wangmeng.arraylist.ExtList;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/7
 * TIME 22:57
 * Description no Description
 **/
public class Test003 {

    public static void main(String[] args) {
//        ExtArrayList extArrayList = new ExtArrayList();
//        extArrayList.add("a");
//        extArrayList.add("b");
//        extArrayList.add("c");
//        extArrayList.add("d");
//        extArrayList.add("e");
//        extArrayList.add("f");
//        extArrayList.remove("f");
////        Object remove = extArrayList.remove(5);
////        System.out.println("remove:"+remove);
//        for(int i=0;i<extArrayList.getSize();i++){
//            System.out.println(extArrayList.get(i));
//        }
        ExtList<String> extList = new ExtArrayList<>();
        extList.add("a");
        extList.add("b");
        extList.add("c");
        extList.add("d");
        extList.add("e");
        for(int i=0;i<extList.getSize();i++){
            System.out.println("extList:"+extList.get(i));
        }
    }
}
