package com.atguigu.srb.base.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author:Eric
 * DATE:2023/2/26-11:56
 * Decription: feign的日志级别配置
 * 所有其他的微服务都能用上
 * NONE :默认级别，不显式日志
 * BASIC: 仅记录请求方法，URL，响应状态及执行时间
 * HEADERS: 除了BASIC中定义的信息之外，还有请求和响应头信息
 * FULL: 除了HEADERS中定义的信息之外，还有请求和响应正文及元数据信息
 */
@Configuration
public class OpenFeignConfig {

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
