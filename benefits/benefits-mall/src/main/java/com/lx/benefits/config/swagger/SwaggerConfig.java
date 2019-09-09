package com.lx.benefits.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("com.lx.benefits.web.controller")).paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("福粒商城api文档").description("restful 风格接口")
                // 服务条款网址
                .termsOfServiceUrl("http://dn.lixiangshop.com/mall")
                .version("1.0")
                .contact(new Contact("wangmeng", "http://dn.lixiangshop.com/mall", "wangmeng@seagoor.com"))
                .build();
    }
}