package com.wangmeng.test;

import java.util.Arrays;

public class Test001 {

    public static void main(String[] args) {
        Object[] objects = {1,2};
        System.out.println(objects.length);
        for(Object object : objects){
            System.out.println("objects:"+object.toString());
        }
        System.out.println("############################################");
        //扩容到10
        Object[] copyNewObjects= Arrays.copyOf(objects, 10);
        System.out.println("copyNewObjects:"+copyNewObjects.length);
        for(int i=0;i<copyNewObjects.length;i++){
            System.out.println(copyNewObjects[i]);
        }

        int[] fun = {0,1,2,3,4,5,6};
        System.arraycopy(fun,3,fun,0,4);
        //长度为7:java.lang.ArrayIndexOutOfBoundsException
        for(int i: fun){
            System.out.println("i:"+i);
        }
    }
}
