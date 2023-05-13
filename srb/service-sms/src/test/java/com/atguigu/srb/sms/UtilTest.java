package com.atguigu.srb.sms;


import com.atguigu.srb.sms.util.SmsProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Author:Eric
 * DATE:2023/4/11-22:04
 * Decription:
 */

/**
 * 整个版本的测试依赖 是：
 * <dependency>
 * <groupId>org.springframework.boot</groupId>
 * <artifactId>spring-boot-starter-test</artifactId>
 * <scope>test</scope>
 * </dependency>
 * 就需要写 RunWith(SpringRunner.class)
 * 如果加了有
 * <scope>test</scope>
 * <exclusion>
 *     <groupId>org.junit.vintage</groupId>
 *     <artifactId>junit-vintage-engine</artifactId>
 * </exclusion>
 * 就不用写
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class) //创建spring上下文环境 需要从spring容器之中获取到这些信息
public class UtilTest {
    @Test
    public void testProperties() {
        System.out.println("===========");
        System.out.println(SmsProperties.KEY_ID);
        System.out.println(SmsProperties.KEY_SECRET);
        System.out.println(SmsProperties.SIGN_NAME);
        System.out.println(SmsProperties.TEMPLATE_CODE);
        System.out.println(SmsProperties.REGION_ID);

    }
}
