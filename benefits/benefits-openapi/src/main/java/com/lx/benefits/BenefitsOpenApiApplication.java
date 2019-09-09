package com.lx.benefits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@PropertySource(value={
        "classpath:properties/common.properties",
        "classpath:properties/metainfo-jdapi.properties",
        "classpath:properties/metainfo.properties",
        "classpath:properties/metainfo-yxapi.properties",
        "classpath:properties/base-supplierinfo.properties"
})
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class BenefitsOpenApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BenefitsOpenApiApplication.class, args);
	}

}
