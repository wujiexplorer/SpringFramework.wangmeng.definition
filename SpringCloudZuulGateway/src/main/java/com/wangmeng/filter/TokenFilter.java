package com.wangmeng.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/15
 * TIME 17:04
 * Description no Description
 **/
//@Component
public class TokenFilter extends ZuulFilter {

    /**
     * Swagger整合Zuul，最好不要传递UserToken（起过滤作用），
     * 否则undefined，至于url的参数在对应的Swagger文档可以
     * 显示出来
     */
    @Value("${server.port}")
    private String serverPort;
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String userToken = request.getParameter("userToken");
        if(StringUtils.isEmpty(userToken)){
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseBody("userToken is null");
            currentContext.setResponseStatusCode(401);
            return null;
        }
        System.out.println("网关服务器端口号："+serverPort);
        return null;
    }
}
