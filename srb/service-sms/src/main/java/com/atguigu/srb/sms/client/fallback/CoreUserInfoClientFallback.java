package com.atguigu.srb.sms.client.fallback;

import com.atguigu.srb.sms.client.CoreUserInfoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Author:Eric
 * DATE:2023/5/22-21:59
 * Decription: 回调，当core服务出问题，远程调用没有响应，服务熔断了，需要有备选方案
 *  远程没有响应 就在这里实现
 */
@Slf4j
@Service
public class CoreUserInfoClientFallback implements CoreUserInfoClient {
    @Override
    public Boolean checkMobile(String mobile) {
        log.error("远程调用失败，服务熔断");
        //手机号不重复
        return false;
    }
}
