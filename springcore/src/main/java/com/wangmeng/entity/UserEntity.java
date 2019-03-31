package com.wangmeng.entity;

public class UserEntity {

    private String useName;
    private Integer age;

    public UserEntity(){
        System.out.println("无参构造器。。。。。。");
    }

    public UserEntity(String useName,Integer age){
        System.out.println("我是有参构造器，userName:"+useName+",age:"+age);
        this.useName = useName;
        this.age = age;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "useName='" + useName + '\'' +
                ", age=" + age +
                '}';
    }
}
