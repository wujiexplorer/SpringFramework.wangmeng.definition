package com.wangmeng.service.impl;

import com.wangmeng.service.OrderService;
import com.wangmeng.spring.extannotation.ExtService;

@ExtService
public class OrderServiceImpl implements OrderService {

    public void addOrder() {
        System.out.println("addOrder......");
    }
}
