### GSON的用法

Gson使用比较方便，使用方法查看下面的几篇文章就行。


[gson使用详解](https://blog.csdn.net/u012702547/article/details/46043549),    [Gson使用方法详解](https://www.cnblogs.com/tony-yang-flutter/p/12350156.html),   [Gson基本用法](https://www.cnblogs.com/baiqiantao/p/7512336.html)

[你真的会用Gson吗?Gson使用指南（一）](https://www.jianshu.com/p/e740196225a4),   [你真的会用Gson吗?Gson使用指南（二）](https://www.jianshu.com/p/c88260adaf5e),   [你真的会用Gson吗?Gson使用指南（三）](https://www.jianshu.com/p/0e40a52c0063),   [你真的会用Gson吗?Gson使用指南（四）](https://www.jianshu.com/p/3108f1e44155)



### GSON的替换Spring默认的Jackson

修改依赖，排除jackson的依赖，引入gson就行。

```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <!-- 排除jackson -->
        <exclusions>
            <exclusion>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-json</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    <!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.8.9</version>
    </dependency>
```

applcaiiton配置如下，修改HTTPMessageConverter， 设置时间格式，禁止HTML转义等，现在不用自己去写了，SpringBoot有配置选项，在applicaiton之中设置就行。

```properties
spring.application.name=boot-gson
# http消息转换器
spring.mvc.converters.preferred-json-mapper=gson
# 时间格式
spring.gson.date-format=yyyy-MM-dd HH:mm:ss
# 禁止转义
spring.gson.disable-html-escaping=true
```



### GSON整合Knief4j/Swagger
#### 依赖

```java
        <!-- knife4j -->
        <dependency>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>knife4j-spring-boot-starter</artifactId>
            <version>2.0.9</version>
        </dependency>
```

#### 配置

```java
@EnableSwagger2WebMvc // 启用Swagger2WebMvc, 这个是Swagger的注解
@Configuration
//@EnableKnife4j
public class Knife4jConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Knife4j")
                        .description("knife4j与采用Gson的Springboot整合")
                        .termsOfServiceUrl("http://localhost:10220/doc.html")
                        .contact(new Contact("z.g.y", "", ""))
                        .version("1.0")
                        .build())
                .groupName("knife4j-gson")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zgy.learn.bootgson.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
```

#### applcaiiton配置

```properties
# knief4j
spring.knief4j.enabled=true
# 开启增强配置
knife4j.enable=true
# 开启生产环境屏蔽(true)
knife4j.production=false
```

还有一些问题Knief4j整合，就是在使用之中转换的问题，所以在Spring项目之中使用默认的Jackson，然后在实际的操作之中，返回Gson。如果非要接入，下面是一种方式，未验证。

[Question, how to use Google Gson instead of the default Jackson of the Spring](https://github.com/springfox/springfox/issues/2758)，[Spring Boot + Gson + Swagger UI Fix Problem](https://www.youtube.com/watch?v=x_lSgM9uxw0)，[Swagger入门和实战（概念和java例子及解决Gson兼容问题）](https://blog.csdn.net/liyiming2017/article/details/83550359)
