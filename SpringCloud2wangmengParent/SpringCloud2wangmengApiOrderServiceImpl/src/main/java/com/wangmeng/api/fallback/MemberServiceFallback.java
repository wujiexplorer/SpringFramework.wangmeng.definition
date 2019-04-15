package com.wangmeng.api.fallback;

import com.wangmeng.api.entity.UserEntity;
import com.wangmeng.base.BaseApiService;
import com.wangmeng.base.ResponseBase;
import com.wangmeng.feign.MemberServiceFeign;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/14
 * TIME 23:37
 * Description no Description
 **/
@Component
public class MemberServiceFallback extends BaseApiService implements MemberServiceFeign {
    @Override
    public UserEntity getMember(String name) {
        return null;
    }

    /**
     * 服务调用超时，就会采用服务熔断、服务降级
     * @return
     */
    @Override
    public ResponseBase getUserInfo() {
        return setResultSuccess("服务器忙，请稍后重试！以类的方式写服务降级");
    }
}
