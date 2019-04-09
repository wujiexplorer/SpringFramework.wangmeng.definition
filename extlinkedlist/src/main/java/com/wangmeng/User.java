package com.wangmeng;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/8
 * TIME 12:07
 * Description no Description
 **/
public class User {

    private String userName;
    private Integer age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
