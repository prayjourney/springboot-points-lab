package com.zgy.learn.bootswaggermailquartzmongoredis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                // .apis的包名要修改成对应的包, 否则报错: No operations defined in spec!
                .apis(RequestHandlerSelectors.basePackage(
                        "com.zgy.learn.bootswaggermailquartzmongoredis.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("SpringBoot整合Swagger")
                        .description("SpringBoot整合Swagger，详细信息......")
                        .version("9.0")
                        .contact(new Contact("最光阴", "cnblogs.com/prayjourney",
                                "helloworld@gmail.com"))
                        .license("The Apache License")
                        .licenseUrl("http://www.baidu.com")
                        .build());
    }

    @Bean
    public Docket createRestApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .groupName("zgy") // 分组名，默认是default
                .select()
                .apis(RequestHandlerSelectors.basePackage(
                        "com.zgy.learn.bootswaggermailquartzmongoredis.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("SpringBoot整合Swagger, boot, swagger, mail, quartz, mongo的学习例子")
                        .description("SpringBoot整合Swagger")
                        .version("3.8")
                        .contact(new Contact("最光阴",
                                "https://www.cnblogs.com/prayjourney/",
                                "zgy@gmail.com"))
                        .license("APACHE LICENSE, VERSION 2.0")
                        .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                        .build());
    }
}