package com.wangmeng.api.service;

import com.wangmeng.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/14
 * TIME 10:01
 * Description no Description
 **/
public interface IOrderService {

    @RequestMapping("/orderToMember")
    public String orderToMember(String name);

    @RequestMapping("/orderToMemberUserInfo")
    public ResponseBase orderToMemberUserInfo();

    @RequestMapping("/orderInfo")
    public ResponseBase orderInfo();

    @RequestMapping("/orderToMemberUserInfoHystrix")
    public ResponseBase orderToMemberUserInfoHystrix();
}
