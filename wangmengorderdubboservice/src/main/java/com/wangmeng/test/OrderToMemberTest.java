package com.wangmeng.test;

import com.wangmeng.api.member.service.MemberService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/17
 * TIME 22:09
 * Description no Description
 **/
public class OrderToMemberTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("consumer.xml");
        MemberService memberService = app.getBean(MemberService.class);
        String result = memberService.getUser(1L);
        System.out.println("订单的服务调用会员服务返回结果：resultUser:"+result);
    }
}
