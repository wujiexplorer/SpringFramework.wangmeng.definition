package com.wangmeng.feign;

import com.wangmeng.api.fallback.MemberServiceFallback;
import com.wangmeng.api.service.IMemberService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/14
 * TIME 10:09
 * Description no Description
 **/
@FeignClient(value = "app-wangmeng-member",fallback = MemberServiceFallback.class)
public interface MemberServiceFeign extends IMemberService {

}
