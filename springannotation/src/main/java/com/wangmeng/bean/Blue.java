package com.wangmeng.bean;

public class Blue {

    public Blue(String color){
        System.out.println("Blue....Constructor.....");
        this.color = color;
    }

    public String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
