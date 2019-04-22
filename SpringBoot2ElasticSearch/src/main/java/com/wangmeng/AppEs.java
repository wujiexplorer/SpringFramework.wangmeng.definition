package com.wangmeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * Created by IntelliJ IDEA
 * USER Administrator
 * DATE 2019/4/21
 * TIME 0:38
 * Description no Description
 **/
@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.wangmeng.repository")
public class AppEs {

    public static void main(String[] args) {
        SpringApplication.run(AppEs.class,args);
    }
}
