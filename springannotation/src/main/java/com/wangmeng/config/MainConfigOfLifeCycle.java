package com.wangmeng.config;

import com.wangmeng.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * bean的生命周期：
 *      bean创建---初始化---销毁的过程
 * 容器管理bean的生命周期
 * 我们可以自定义初始化和销毁方法：容器在bean进行到当前生命周期的时候来调用我们自定义的初始化和销毁方法
 * 1)指定初始化和销毁方法
 *      指定init-method和destroy-method
 */

@ComponentScan("com.wangmeng.bean")
@Configuration
public class MainConfigOfLifeCycle {

    @Bean(initMethod = "init",destroyMethod = "destroy")
    //@Scope("prototype")
    public Car car(){
        return new Car();
    }
}
