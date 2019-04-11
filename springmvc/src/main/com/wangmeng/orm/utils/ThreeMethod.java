package com.wangmeng.orm.utils;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/11
 * TIME 22:02
 * Description no Description
 **/
public class ThreeMethod {

    public synchronized void sayHello1()throws Exception{
        System.out.println("sayHello1");
        Thread.sleep(3000);
        System.out.println("wait 3 seconds!");
    }

    public void sayHello2(){
        System.out.println("sayHello2");
    }

    public void sayHello3(){
        System.out.println("sayHello3");
    }

    public static void main(String[] args)throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    new ThreeMethod().sayHello1();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                new ThreeMethod().sayHello2();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                new ThreeMethod().sayHello3();
            }
        }).start();
    }
}
