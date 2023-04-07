package com.atguigu.srb.core;

import com.atguigu.srb.core.mapper.DictMapper;
import com.atguigu.srb.core.pojo.entity.Dict;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Author:Eric
 * DATE:2023/4/5-16:08
 * Decription: 引入redis 测试存取值
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTemplateTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private DictMapper dictMapper;

    @Test
    public void save(){
        Dict dict = dictMapper.selectById(1);
        redisTemplate.opsForValue().set("dictTest",dict,10, TimeUnit.SECONDS);

    }
    @Test
    public void get(){
        String dict = redisTemplate.opsForValue().get("dict").toString();
        System.out.println("获取redis中dict的数据:"+dict);
    }

}
