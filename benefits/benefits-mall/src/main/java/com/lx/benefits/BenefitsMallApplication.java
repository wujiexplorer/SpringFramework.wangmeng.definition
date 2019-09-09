package com.lx.benefits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@PropertySource(value={
        "classpath:properties/common.properties",
        "classpath:properties/metainfo-jdapi.properties",
        "classpath:properties/metainfo.properties",
        "classpath:properties/metainfo-yxapi.properties",
        "classpath:properties/base-supplierinfo.properties"
})
@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.lx.benefits.essearch.repository")
public class BenefitsMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(BenefitsMallApplication.class, args);
    }

}

