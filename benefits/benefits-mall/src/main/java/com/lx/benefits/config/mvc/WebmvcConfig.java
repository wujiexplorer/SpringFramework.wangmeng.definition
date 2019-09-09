package com.lx.benefits.config.mvc;


import com.lx.benefits.config.interceptor.AuthSecurityInterceptor;
import com.lx.benefits.config.interceptor.EnterpriseAuthSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration("webmvcConfig")
public class WebmvcConfig implements WebMvcConfigurer {

    @Autowired
    private EnterpriseAuthSecurityInterceptor enterpriseAuthSecurityInterceptor;

    @Autowired
    private AuthSecurityInterceptor authSecurityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //全局处理拦截，和业务无关的
        registry.addInterceptor(authSecurityInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/webjars/**", "/swagger-resources/**")
                //.excludePathPatterns("/swagger-ui.html/**")
                .excludePathPatterns("/v2/api-docs/**")
                .excludePathPatterns("/templates/watermark/**");

        registry.addInterceptor(enterpriseAuthSecurityInterceptor)
                .addPathPatterns("/enterprise/**")
                .addPathPatterns("/benefits/**")
                .addPathPatterns("/common/upload/**")
                .excludePathPatterns("/enterprise/account/login")
                .excludePathPatterns("/enterprise/grain/getGrainArticleInfoNoLanding")
                .excludePathPatterns("/enterprise/account/wx/login")
                .excludePathPatterns("/enterprise/account/clientlogin")
                .excludePathPatterns("/enterprise/account/loginout")
                .excludePathPatterns("/enterprise/cards/verify")
                .excludePathPatterns("/enterprise/cards/smsCodeCheck")
                .excludePathPatterns("/enterprise/cards/smscode")
                .excludePathPatterns("/enterprise/cards/bind")
                .excludePathPatterns("/enterprise/wxacode/login")
                .excludePathPatterns("/enterprise/goods/introduction/**")
                .excludePathPatterns("/pay/**");

    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
