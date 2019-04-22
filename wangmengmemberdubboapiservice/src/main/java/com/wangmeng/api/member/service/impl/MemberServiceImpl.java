package com.wangmeng.api.member.service.impl;

import com.wangmeng.api.member.service.MemberService;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/17
 * TIME 21:17
 * Description no Description
 **/
public class MemberServiceImpl implements MemberService {
    public String getUser(Long userId) {
        System.out.println("订单服务调用会员服务：userId"+userId);
        return "wangmengok";
    }
}
