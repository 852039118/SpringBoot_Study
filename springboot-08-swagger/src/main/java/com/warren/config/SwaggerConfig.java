package com.warren.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2     // 开启Swagger2
public class SwaggerConfig {
    // 配置了Swagger的Docket的bean实例
    @Bean
    public Docket docket(Environment environment){

        // 设置要显示的swagger环境
        Profiles profiles = Profiles.of("test", "dev");
        // 通过environment.acceptsProfiles判断当前是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("Warren")
                .enable(flag)//enable 是否启动swagger，如果为false，则不能在浏览器中访问swagger
                .select()
                // RequestHandlerSelectors,配置要扫描接口的方式
                .apis(RequestHandlerSelectors.basePackage("com.warren.controller"))
                // 过滤掉什么路径
                //.paths(PathSelectors.ant("/warren/**"))
                .build();
    }

    private ApiInfo apiInfo(){
        Contact DEFAULT_CONTACT = new Contact("Warren", "http://warren.run/", "852039118@qq.com");
        return new ApiInfo(
                "Warren的SwaggerAPI文档",
                "即使再小的帆也能远航",
                "2.0",
                "http://warren.run/",
                DEFAULT_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

    @Bean
    public Docket docketA(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("分组A");
    }

    @Bean
    public Docket docketB(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("分组B");
    }

    @Bean
    public Docket docketC(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("分组C");
    }
}
