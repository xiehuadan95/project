package com.atguigu.srb.base.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket adminApiConfig(){
        //创建文档对象 加入类型 可用组名
        //分组管理不同类型的接口 添加过滤器 将带有/admin/的过滤出来
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(adminApiInfo())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/admin/.*")))
                .build();
    }
    //修改swagger网页端 页面的描述
    private ApiInfo adminApiInfo(){
    return     new ApiInfoBuilder().title("尚融宝后台管理系统API文档")
                .description("本文档描述了尚融宝后台管理系统的各个模式接口的调用方式")
                .version("1.1")
                .contact(new Contact("eric","http://atguigu.com","xiehuadan@aliyun.com"))
                .build();

    }
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/api/.*")))
                .build();
    }

    private ApiInfo webApiInfo(){
        return     new ApiInfoBuilder().title("尚融宝网站API文档")
                .description("本文档描述了尚融宝网站管理系统的各个模式接口的调用方式")
                .version("1.1")
                .contact(new Contact("eric","http://atguigu.com","xiehuadan@aliyun.com"))
                .build();

    }
}
