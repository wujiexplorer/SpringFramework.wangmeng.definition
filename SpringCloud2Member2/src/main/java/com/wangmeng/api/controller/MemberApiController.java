package com.wangmeng.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/10
 * TIME 23:01
 * Description no Description
 **/
@RestController
public class MemberApiController {


    @Value("${server.port}")
    private String serverPort;

    /**
     * Spring Cloud内部做了中文乱码处理
     * @return
     */
    @RequestMapping("/getMember")
    public String getMember(){
        return "this is member,我是会员方服务，Spring Cloud!"+serverPort;
    }
}
