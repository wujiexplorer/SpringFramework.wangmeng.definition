package com.wangmeng.api.service.impl;

import com.wangmeng.api.entity.UserEntity;
import com.wangmeng.api.service.IMemberService;
import com.wangmeng.base.BaseApiService;
import com.wangmeng.base.ResponseBase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/13
 * TIME 23:29
 * Description no Description
 **/
@RestController
public class MemberServiceImpl extends BaseApiService implements IMemberService {

    @Value("${server.port}")
    private String serverPort;

    @Override
    @RequestMapping("/getMember")
    public UserEntity getMember(@RequestParam("name") String name) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name+serverPort);
        userEntity.setAge(30);
        return userEntity;
    }

    @Override
    @RequestMapping("/getUserInfo")
    public ResponseBase getUserInfo() {
        System.out.println("我是会员服务，订单服务调用会员服务。。。。。  ");
        try{
            Thread.sleep(1500);
        }catch (Exception e){
            e.printStackTrace();
        }
        return setResultSuccess("订单服务接口调用会员服务接口成功。。。。"+serverPort);
    }
}
