package com.atguigu.srb.sms.util;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Author:Eric
 * DATE:2023/4/11-21:43
 * Decription: 工具类 读取配置文件的配置项
 * InitializingBean spring提供的接口 其中有一个方法，会在 属性被设置后自动 运行
 */
//spring 自动去组装 可生成配置文件的元数据描述信息对象


@Data
@Component
@ConfigurationProperties(prefix = "aliyun.sms")
public class SmsProperties implements InitializingBean {

    private String regionId;
    private String keyId;
    private String keySecret;
    private String templateCode;
    private String signName;

    //常量的形式使用
    public static String REGION_ID;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String TEMPLATE_CODE;
    public static String SIGN_NAME;

//属性填充后 自动调用此方法
    @Override
    public void afterPropertiesSet() throws Exception {

        REGION_ID=regionId;
        KEY_ID=keyId;
        KEY_SECRET=keySecret;
        TEMPLATE_CODE=templateCode;
        SIGN_NAME=signName;

    }
}
