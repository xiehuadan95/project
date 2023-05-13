package com.xie.study.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author:Eric
 * DATE:2023/4/25-22:57
 * Decription: 声明自定义注解  适用于不同业务的数据源 AOP方式切换数据源
 * @Target 元注解，可声明在哪里
 * RetentionPolicy.RUNTIME   会被JVM 加载，可以通过反射的方式获取 当前类上是否有自定义的这个WR注解
 * RetentionPolicy.SOURCE    不会编译到class文件上面，通过反射就获取不到
 * RetentionPolicy.CLASS     会保留在class文件上面，不会被JVM加载，反射也获取不到，字节码方式能获取到
 */

@Target({ElementType.METHOD,ElementType.TYPE})
//保留方式 会被JVM 加载，可以通过反射的方式获取 当前类上是否有自定义的这个WR注解
@Retention(RetentionPolicy.RUNTIME)
public @interface WR {
    String value() default "W";
}
