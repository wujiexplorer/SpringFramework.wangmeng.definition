package com.wangmeng.api.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/13
 * TIME 20:28
 * Description no Description
 **/

/**
 * 该接口一定要在AppOrder启动类的同级或之下级，否则无法扫描到
 */
@FeignClient(name = "app-wangmeng-member")
public interface MemberApiFeign {

    @RequestMapping("/getMember")
    String getMember();
}
