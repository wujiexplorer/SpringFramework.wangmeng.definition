package com.wangmeng.config;

import com.wangmeng.bean.Person;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

//配置类==配置文件
@Configuration   //告诉Spring只是一个配置类
//@ComponentScan(value="com.wangmeng",includeFilters =  {
//        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class, Service.class})
//},useDefaultFilters = false)
@ComponentScans(
        value = {
                @ComponentScan(value="com.wangmeng",includeFilters =  {
                        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class, Service.class})
                },useDefaultFilters = false)
        }
)
//@ComponentScan value:指定要扫描的包
//excludeFilter = Filter[],指定扫描的时候按照什么规则排除那些组件
//includeFilter = Filter[],指定扫描的时候只需要包含哪些组件
public class MainConfig {

    //给容器注册一个Bean;类型为返回值类型,id默认用方法名作为id
    @Bean("person")//优先级高些
    public Person person01(){
        return new Person("lisi",20);
    }
}
