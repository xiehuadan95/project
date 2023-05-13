package com.atguigu.srb.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Author:Eric
 * DATE:2023/5/13-17:54
 * Decription:
 */
@SpringBootApplication
@ComponentScan({"com.atguigu.srb","com.atguigu.srb.common"})
public class ServiceOssApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOssApplication.class,args);
    }
}
