package com.lx.benefits.config.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lx.benefits.interceptor.AdminAuthSecurityInterceptor;
import com.lx.benefits.interceptor.AgentAdminAuthSecurityInterceptor;
import com.lx.benefits.interceptor.AuthSecurityInterceptor;
import com.lx.benefits.interceptor.CustomerAdminAuthSecurityInterceptor;
import com.lx.benefits.interceptor.EnterpriseAdminAuthSecurityInterceptor;
import com.lx.benefits.interceptor.EnterpriseAuthSecurityInterceptor;
import com.lx.benefits.interceptor.PowerInterceptor;
import com.lx.benefits.interceptor.SupplierAuthSecurityInterceptor;

@Configuration("webmvcConfig")
public class WebmvcConfig implements WebMvcConfigurer {

    @Autowired
    private AuthSecurityInterceptor authSecurityInterceptor;

    @Autowired
    private EnterpriseAdminAuthSecurityInterceptor enterpriseAdminAuthSecurityInterceptor;

    @Autowired
    private EnterpriseAuthSecurityInterceptor enterpriseAuthSecurityInterceptor;

    @Autowired
    private SupplierAuthSecurityInterceptor supplierAuthSecurityInterceptor;

    @Autowired
    private AdminAuthSecurityInterceptor adminAuthSecurityInterceptor;
    @Autowired
    private AgentAdminAuthSecurityInterceptor agentAdminAuthSecurityInterceptor;
    @Autowired
    private CustomerAdminAuthSecurityInterceptor customerAdminAuthSecurityInterceptor;
    @Autowired
    private PowerInterceptor powerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //全局处理拦截，和业务无关的
        registry.addInterceptor(authSecurityInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/webjars/**", "/swagger-resources/**")
                //.excludePathPatterns("/swagger-ui.html/**")
                .excludePathPatterns("/v2/api-docs/**")
                .excludePathPatterns("/templates/watermark/**");


        registry.addInterceptor(enterpriseAdminAuthSecurityInterceptor)
                .addPathPatterns("/enterpriseadmin/**")
                .addPathPatterns("/common/upload/saveenterprisefile")
                .excludePathPatterns("/enterpriseadmin/account/login")
                .excludePathPatterns("/enterpriseadmin/account/loginout");
        
        registry.addInterceptor(agentAdminAuthSecurityInterceptor)
                .addPathPatterns("/agentadmin/**")	
                .addPathPatterns("/common/upload/saveagentfile")
                .excludePathPatterns("/agentadmin/account/login")
                .excludePathPatterns("/agentadmin/account/loginout");


        registry.addInterceptor(enterpriseAuthSecurityInterceptor)
                .addPathPatterns("/enterprise/**")
                .addPathPatterns("/common/upload/saveemployeefile")
                .excludePathPatterns("/enterprise/account/login")
                .excludePathPatterns("/enterprise/account/loginout");


        registry.addInterceptor(supplierAuthSecurityInterceptor)
                .addPathPatterns("/supplieradmin/**")
                .addPathPatterns("/common/upload/savesupplierfile")
                .excludePathPatterns("/supplieradmin/account/login")
                .excludePathPatterns("/supplieradmin/account/loginout");

        registry.addInterceptor(adminAuthSecurityInterceptor)
                .addPathPatterns("/admin/**")
                .addPathPatterns("/common/upload/saveadminfile")
                .excludePathPatterns("/admin/account/login")
                .excludePathPatterns("/admin/account/loginout");

        registry.addInterceptor(powerInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/account/login")
                .excludePathPatterns("/admin/account/loginout");
        
        registry.addInterceptor(customerAdminAuthSecurityInterceptor)
				.addPathPatterns("/customeradmin/**")	
				.addPathPatterns("/common/upload/saveagentfile")
				.excludePathPatterns("/customeradmin/account/login")
				.excludePathPatterns("/customeradmin/account/loginout");
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
