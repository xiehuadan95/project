package com.xie.study.plugin;

import com.xie.study.DynamicDataSource;
import com.xie.study.common.CommonEnum;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;


/**
 * Author:Eric
 * DATE:2023/4/16-18:04
 * Decription: 基于mybatis 插件的多数据源 插件中设置数据源标识
 * 适用于读写分离 要将这个插件配置为一个bean
 *
 */
//增删改 都会调用update方法 这里对update  和 query进行了代理
@Intercepts(
        {@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class DynamicDataSourcePlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //拿到 当前方法（upadate,query)所有参数
        Object[] args = invocation.getArgs();
        //不管多少个，第0个都是MappedStatement 封装SQL到mappedstatement  CRUD 所有的元素和sql
        MappedStatement ms = (MappedStatement) args[0];
        //读方法 拿到当前sql的类型
        if (ms.getSqlCommandType().equals(SqlCommandType.SELECT)) {
            DynamicDataSource.name.set(CommonEnum.DATASOURCE_READ.getDataSourceType());
        } else {
            //写方法
            DynamicDataSource.name.set(CommonEnum.DATASOURCE_WRITE.getDataSourceType());
        }
        //修改当前线程要选择的数据源的Key
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }

    }

    @Override
    public void setProperties(Properties properties) {

    }
}
