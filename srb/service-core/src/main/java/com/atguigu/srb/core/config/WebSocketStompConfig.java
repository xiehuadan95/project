package com.atguigu.srb.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Author:Eric
 * DATE:2023/6/3-12:56
 * Decription: websocket配置类 配置自己
 */
@Configuration
public class WebSocketStompConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
