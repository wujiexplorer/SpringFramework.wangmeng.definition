package com.wangmeng.ext;


import com.wangmeng.bean.Blue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("com.wangmeng.ext")
@Configuration
public class ExtConfig {

    @Bean
    public Blue blue(){
        return new Blue("blue");
    }
}
