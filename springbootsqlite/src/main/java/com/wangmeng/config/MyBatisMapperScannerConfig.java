package com.wangmeng.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MyBatisMapperScannerConfig {
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");

        //com.example.demo.dal.mapper 这个包名是所有的Mapper.java文件所在的路径，该包下面的子包里面的文件同样会扫描到。
        //此包名与具体的应用的名称相关
        mapperScannerConfigurer.setBasePackage("com.wangmeng.mapper");

        return mapperScannerConfigurer;
    }

}