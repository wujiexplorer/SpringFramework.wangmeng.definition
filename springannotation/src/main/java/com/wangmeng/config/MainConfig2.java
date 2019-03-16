package com.wangmeng.config;

import com.wangmeng.bean.Person;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
public class MainConfig2 {
    /**
     *	 * {@link ConfigurableBeanFactory#SCOPE_SINGLETON SCOPE_SINGLETON}.
     * 	 * @since 4.2
     * 	 * @see ConfigurableBeanFactory#SCOPE_PROTOTYPE prototype
     * 	 * @see ConfigurableBeanFactory#SCOPE_SINGLETON singleton
     * 	 * @see org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST request
     * 	 * @see org.springframework.web.context.WebApplicationContext#SCOPE_SESSION session
     * @Scope:调整作用域
     * 	 prototype:多实例的:ioc容器启动并不会去调用方法创建对象放在容器中。
     * 	        每次获取的时候才会调用方法创建对象。
     * 	 singleton:单实例的（默认的):ioc容器启动会调用方法创建对象放到ioc容器中。
     * 	 以后每次获取就是直接从容器(map.get())中拿
     * 	 request:同一次请求创建一个实例
     * 	 session:同一个session创建创建一个实例
     * 	 懒加载
     * 	        单实例bean：默认在容器启动的时候创建对象；
     * 	        懒加载：容器启动不创建对象，第一次使用（获取）Bean创建对象，并初始化；
     * @return
     */
    //默认是单实例
   // @Scope("prototype")
    @Scope
    @Lazy
    @Bean("person")
    public Person person(){
        System.out.println("给容器中添加Person...");
        return  new Person("张三",25);
    }
}
