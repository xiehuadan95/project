package com.xie.study.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;


import com.xie.study.plugin.DynamicDataSourcePlugin;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.sql.DataSource;

/**
 * Author:Eric
 * DATE:2023/4/16-11:28
 * Decription: 读取yml文件中的 数据源的配置信息
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.datasource1")
    public DataSource dataSource1(){
        //底层会自动拿到spring.datasource 中的配置，创建一个DruidDataSource
        return DruidDataSourceBuilder.create().build();
    }
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.datasource2")
    public DataSource dataSource2(){
        //底层会自动拿到spring.datasource 中的配置，创建一个DruidDataSource
        return DruidDataSourceBuilder.create().build();
    }
    //将插件配置为一个bean  使用AOP的方式的时候需要注释掉
  /*  @Bean
    public Interceptor DynamicDataSourcePlugin(){
        return  new DynamicDataSourcePlugin();
    }*/

}
