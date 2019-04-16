package com.wangmeng.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/16
 * TIME 20:55
 * Description no Description
 **/
@RestController
@Api("Swagger控制器")
public class SwaggerController {

    @ApiOperation("swagger演示接口")
    @GetMapping("/swaggerIndex")
    public String swaggerIndex(){
        System.out.println("SwaggerIndex");
        return "swaggerIndex";
    }

    @ApiOperation("获取会员相关信息")
    @ApiImplicitParam(name = "userName",value = "用户信息",required = true,dataType = "String")
    @PostMapping("/getMember")
    public String getMember(String userName){

        System.out.println("userNaem:"+userName);
        return "userName:"+userName;
    }
}
