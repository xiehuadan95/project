package com.xie.study.aspect;

import com.xie.study.DynamicDataSource;
import com.xie.study.annotion.WR;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Author:Eric
 * DATE:2023/4/25-23:06
 * Decription: 声明一个切面类 AOP+自定义注解
 */
@Component
@Aspect
public class DynamicDataSourceAspect {

    //用前置通知 或环绕通知 ,只需要匹配service 包下面的bean 需要精确一下，不需要所有的包都去匹配 浪费性能
    //execution()能更精确到哪个方法 within() 次之 这个包所有的类 且有这个注解的都会被增强
    @Before("within(com.xie.study.service.impl.*) && @annotation(wr)")
    public void before(JoinPoint point, WR wr){  //连接点 joinpoint  ,自定义注解
        String name = wr.value();
        DynamicDataSource.name.set(name);
        System.out.println("获取到的数据源标识"+name);

    }

}
