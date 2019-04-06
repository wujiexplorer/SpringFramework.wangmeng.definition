package com.wangmeng.service.impl;

import com.wangmeng.service.OrderService;
import com.wangmeng.service.UserService;
import com.wangmeng.spring.extannotation.ExtResource;
import com.wangmeng.spring.extannotation.ExtService;

import javax.annotation.Resource;

@ExtService
public class UserServiceImpl implements UserService {

    @ExtResource
    private OrderService orderServiceImpl;

    public void add() {
        orderServiceImpl.addOrder();
        System.out.println("使用java反射机制初始化对象。。。。。");
    }
}
