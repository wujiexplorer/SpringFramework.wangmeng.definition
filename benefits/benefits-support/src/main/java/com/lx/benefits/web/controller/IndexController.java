package com.lx.benefits.web.controller;

import com.apollo.starter.web.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页
 **/
@RestController
@RequestMapping("/index")
public class IndexController {

    @GetMapping("/")
    public Result<?> index(){
        return Result.wrapDefaultSuccessResult();
    }

}