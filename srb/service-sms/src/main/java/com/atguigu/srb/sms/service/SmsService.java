package com.atguigu.srb.sms.service;

import java.util.Map;

/**
 * Author:Eric
 * DATE:2023/5/13-15:32
 * Decription:
 */
public interface SmsService {
    /**
     *
     * @param mobile      手机号
     * @param templateCode 短信模板
     * @param param        参数
     */
    void send(String mobile, String templateCode, Map<String,Object> param);

}
