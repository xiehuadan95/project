package com.atguigu.srb.base.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * Author:Eric
 * DATE:2023/2/16-22:06
 * Decription: Redi的配置类 解决 jdk原有序列化存值问题减小空间占用 放到base里面 以后其他模块也要用
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String,Object> redisTemplate(LettuceConnectionFactory redisConnecctionFactory){

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //设置连接池工厂 使用Lettuce连接池
        redisTemplate.setConnectionFactory(redisConnecctionFactory);

        //首先解决key的序列化方式 默认情况下用的jdk的序列化
        //这里用字符串序列化方案 为key的序列化方案
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);

        //解决value的序列化方式 用json序列化方案
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //这里将类的数据类型存入json 便于反序列化的时候转换为正确的数据类型
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

        //ObjectMapper专门处理类映射
        ObjectMapper objectMapper = new ObjectMapper();
        //把当前序列化的对象的数据类型也存入序列化的结果字符串中 激活默认类型 数据类型非 final
        //反序列化的时候 可用反射获取 反序列化类型
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,ObjectMapper.DefaultTyping.NON_FINAL);

        //禁用 dateTime这种类型 序列化localDateTime的问题
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        //用fastjson指定的时间序列化方案
        objectMapper.registerModule(new JavaTimeModule());

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);


        return redisTemplate;
    }


}
