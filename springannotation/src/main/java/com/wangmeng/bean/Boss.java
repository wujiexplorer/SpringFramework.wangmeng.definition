package com.wangmeng.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Boss {

    private Car car;

    //@Autowired
    //一个有参构造器，@Autowired可以省略
    public Boss( Car car1){
        this.car = car1;
        System.out.println("Boss...有参构造器");
    }

    public Car getCar() {
        return car;
    }

    //@Autowired
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }
}
