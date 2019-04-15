package com.wangmeng.api.service;

import com.wangmeng.api.entity.UserEntity;
import com.wangmeng.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/13
 * TIME 22:59
 * Description no Description
 **/
public interface IMemberService {

    /**
     * @RequestParam传递参数时，微服务调用时，这是强制
     * 要求，否则参数传递不进去的，null
     * @param name
     * @return
     */
    @RequestMapping("/getMember")
    public UserEntity getMember(@RequestParam("name") String name);

    @RequestMapping("/getUserInfo")
    public ResponseBase getUserInfo();
}
