package com.atguigu.srb.gateway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * Author:Eric
 * DATE:2023/5/23-20:53
 * Decription:  网关跨域问题配置
 * 跟之前的跨域注解冲突 需要注释掉
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);  //是否运行携带cookie  cookie也运行跨域
        config.addAllowedOrigin("*");  //可接受的域，是一个具体域名或者* （代表任意域名）
        config.addAllowedHeader("*");  //允许携带的头 请求头
//        config.addAllowedHeader("token");  头只允许 携带 token
        config.addAllowedMethod("*");  //允许访问的方式
//        config.addAllowedMethod("get,post"); 允许访问的方式

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //所有的前端服务器访问 所有的url 都按照这个策略执行
        source.registerCorsConfiguration("/**",config);
        return new CorsWebFilter(source);

    }

}
