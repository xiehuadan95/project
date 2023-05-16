package com.atguigu.srb.sms.client;

import com.atguigu.srb.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Author:Eric
 * DATE:2023/5/16-23:04
 * Decription: 代表远程调用的客户端 需要调用 core-service
 * 会去跟service-core建立连接
 */
@FeignClient(value="service-core")
public interface CoreUserInfoClient {
    //接口帮助我们 去调用core里面的 校验手机号是否注册接口
    //本地调用 它就相当于 去访问了 service-core 的 这个userInfo的接口
    @GetMapping("/api/core/userInfo/checkMobile/{mobile}")
     Boolean checkMobile(@PathVariable String mobile);
}
