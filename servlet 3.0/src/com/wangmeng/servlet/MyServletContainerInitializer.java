package com.wangmeng.servlet;

import com.wangmeng.service.HelloService;


import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;


@HandlesTypes(value = {HelloService.class})
public class MyServletContainerInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("刚兴趣的类型：");
        for(Class<?> clazz : set){
            System.out.println(clazz);
        }
        ServletRegistration.Dynamic servlet = servletContext.addServlet("userServlet",new UserServlet());
        servlet.addMapping("/user");

        servletContext.addListener(UserListener.class);
        //Filter不起作用
        FilterRegistration.Dynamic filter = servletContext.addFilter("userFilter",UserFilter.class);
        filter.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST),true,"/hello");
    }
}
