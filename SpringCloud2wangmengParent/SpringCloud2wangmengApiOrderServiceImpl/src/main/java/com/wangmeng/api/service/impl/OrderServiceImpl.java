package com.wangmeng.api.service.impl;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wangmeng.api.entity.UserEntity;
import com.wangmeng.api.service.IOrderService;
import com.wangmeng.base.BaseApiService;
import com.wangmeng.base.ResponseBase;
import com.wangmeng.feign.MemberServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/14
 * TIME 9:46
 * Description no Description
 **/
@RestController
public class OrderServiceImpl extends BaseApiService implements IOrderService {

    @Autowired
    private MemberServiceFeign memberServiceFeign;

    @Override
    @RequestMapping("/orderToMember")
    public String orderToMember(@RequestParam("name") String name) {
        UserEntity userEntity = memberServiceFeign.getMember(name);
        return userEntity==null?"没有找到用户信息":userEntity.toString();
    }

    @Override
    @RequestMapping("/orderToMemberUserInfo")
    public ResponseBase orderToMemberUserInfo() {
        System.out.println("orderToMemberUserInfo:"+"线程池名称："+Thread.currentThread().getName());
        return memberServiceFeign.getUserInfo();
    }

    @Override
    @RequestMapping("/orderInfo")
    public ResponseBase orderInfo() {
        System.out.println("orderInfo:"+"线程池名称："+Thread.currentThread().getName());
        return setResultSuccess();
    }

    /**
     *  @HystrixCommand默认开启服务隔离（线程池隔离）、服务降级、服务熔断
     *  Hystrix默认时间是10s
     * @return
     */
    @HystrixCommand(fallbackMethod = "orderToMemberUserInfoHystrixFallBack")
    @RequestMapping("/orderToMemberUserInfoHystrix")
    @Override
    public ResponseBase orderToMemberUserInfoHystrix() {
        System.out.println("orderToMemberUserInfoHystrix:"+"线程池名称："+Thread.currentThread().getName());
        return memberServiceFeign.getUserInfo();
    }

    @RequestMapping("/orderToMemberUserInfoHystrix_demo2")
    public ResponseBase orderToMemberUserInfoHystrix_demo2() {
        System.out.println("orderToMemberUserInfoHystrix:"+"线程池名称："+Thread.currentThread().getName());
        return memberServiceFeign.getUserInfo();
    }

    public ResponseBase orderToMemberUserInfoHystrixFallBack(){
        return setResultSuccess("返回一个友好的提示：服务降级：服务器忙，请稍后重试！");
    }
}
