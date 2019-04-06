package com.wangmeng.proxy;

import com.wangmeng.service.UserService;

public class UserServiceProxy {

    private UserService userService;

    public UserServiceProxy(UserService userService){
        this.userService = userService;
    }

    public void add(){
        System.out.println("静态代理  开启事务。。。");
        userService.add();
        System.out.println("静态代理   提交事务。。。。");
    }

}
