package com.wangmeng.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
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
@Api("会员服务接口")
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

    @ApiOperation("获取会员相关信息")
    @ApiImplicitParam(name = "userName",value = "用户信息",required = true,dataType = "String")
    @PostMapping("/getMember")
    public String getMember(String userName){

        System.out.println("userNaem:"+userName);
        return "userName:"+userName;
    }
}
