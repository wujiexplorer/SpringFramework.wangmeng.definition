package com.wangmeng;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/8
 * TIME 10:48
 * Description no Description
 **/
public class Test001 {

    public static void main(String[] args) {
        List<String> linkedList = new LinkedList<String>();
        linkedList.add("a");
        linkedList.add("b");
        System.out.println("size:"+linkedList.size());
        for(String str : linkedList){
            System.out.println(str);
        }

        User user = new User();
        //实例化后，属性都为null
        //User{userName='null', age=null}
        System.out.println(user.toString());
    }
}
