package com.xie.dynamic.mybatis.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/**
 * Author:Eric
 * DATE:2023/4/26-21:58
 * Decription:
 * 写数据源配置
 * 继承mybatis
 * 1.指定需要扫描的mapper接口包（主库）
 * 2.指定使用sqlSessionFactory是哪个
 */
@Configuration
@MapperScan(basePackages = "com.xie.dynamic.mybatis.mapper.w",
        sqlSessionFactoryRef="wSqlSessionFactory")
public class WMyBatisConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.datasource2")
    public DataSource dataSource2(){
        //底层会自动拿到spring.datasource 中的配置，创建一个DruidDataSource
        return DruidDataSourceBuilder.create().build();
    }
    @Bean
    @Primary
    public SqlSessionFactory wSqlSessionFactory(@Qualifier("dataSource2") DataSource dataSource2) throws Exception {
        final SqlSessionFactoryBean sessionFactory =new SqlSessionFactoryBean();
        //指定主库
        sessionFactory.setDataSource(dataSource2);
        //指定主库对应的mapper xml文件
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResource("classpath:mapper/w/*.xml"));
        /*主库设置sql控制台打印*/
//        org.apache.ibatis.session.Configuration configuration=new org.apache.ibatis.session.Configuration();
//        configuration.setLogImpl(StdOutImpl.class);
//        sessionFactory.setConfiguration(configuration);
        return sessionFactory.getObject();
    }
    //配置主的事务管理器
    @Bean
    public DataSourceTransactionManager wTransactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource2());
        return dataSourceTransactionManager;

    }

    /**
     * 编程式事务
     * @return
     */
    @Bean
    public TransactionTemplate wtransactionTemplate(){
        //将对应的事务管理器传进去
        return new TransactionTemplate(wTransactionManager());
    }


}
