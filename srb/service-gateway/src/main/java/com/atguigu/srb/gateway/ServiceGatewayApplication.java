package com.atguigu.srb.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Author:Eric
 * DATE:2023/5/23-20:38
 * Decription:
 */
@SpringBootApplication
@EnableDiscoveryClient   //开启服务注册与发现功能
public class ServiceGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceGatewayApplication.class,args);
    }

}
