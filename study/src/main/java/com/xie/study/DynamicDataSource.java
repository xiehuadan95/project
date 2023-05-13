package com.xie.study;

import com.xie.study.common.CommonEnum;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


/**
 * Author:Eric
 * DATE:2023/4/16-14:44
 * Decription: 自定义 多数据源实现  不需要写 多数据源的切换逻辑，父类已经帮忙做了
 * 自定义的作为主要注入的，当出现了相同的 datasource 就 用这个，不会使用 config里面配置的
 * 实现了 InitializingBean 是一个回调接口
 */
@Component
@Primary    //将该Bean设置为主要注入Bean
public class DynamicDataSource extends AbstractRoutingDataSource {
//当前使用的数据源标识  保证线程安全
    public static ThreadLocal<String> name =new ThreadLocal<>();
    //写
    @Autowired
    DataSource dataSource1;
    @Autowired
    DataSource dataSource2;
    //返回当前 数据源标识 就可以了
    @Override
    protected Object determineCurrentLookupKey() {

        return name.get();
    }

    //父类其实进行了 复制一份targetDataSource到 resolvedDataSource中， resolvedDataSource是负责最终切换数据源的 map
    //要告诉 AbstractRoutingDataSource 所有的数据源得提供 父类已经实现了InitializingBean 接口  需要重写一下
    @Override
    public void afterPropertiesSet() {
        //调用父类的方法之前， 为targetDataSource 初始化所有数据源
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(CommonEnum.DATASOURCE_READ.getDataSourceType(),dataSource1);
        targetDataSource.put(CommonEnum.DATASOURCE_WRITE.getDataSourceType(),dataSource2);
        super.setTargetDataSources(targetDataSource);

        //为defaultTargetDataSource 设置默认的数据源
        super.setDefaultTargetDataSource(dataSource2);
        super.afterPropertiesSet();
    }

//    @Override
//    protected Object determineCurrentLookupKey() {
//        return name.get();
//    }

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        //TODO 初始化
//        //初始化，默认的数据源标识为写
//        name.set("W");
//    }
    //返回具体使用的 当前数据源标识
//    @Override
//    protected Object determineCurrentLookupKey() {
//        return name.get();
//    }


}
