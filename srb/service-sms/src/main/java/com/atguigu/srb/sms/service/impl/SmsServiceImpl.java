package com.atguigu.srb.sms.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.atguigu.srb.common.exec.BusinessException;
import com.atguigu.srb.common.myassert.Assert;
import com.atguigu.srb.common.result.ResponseEnum;
import com.atguigu.srb.sms.service.SmsService;
import com.atguigu.srb.sms.util.SmsProperties;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:Eric
 * DATE:2023/5/13-15:34
 * Decription: 短信服务实现
 */
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {
    @Override
    public void send(String mobile, String templateCode, Map<String, Object> param) {
        //创建远程连接客户端对象
        DefaultProfile profile = DefaultProfile.getProfile(SmsProperties.REGION_ID,
                SmsProperties.KEY_ID,
                SmsProperties.KEY_SECRET);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        //创建远程连接的请求参数
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");

        request.putQueryParameter("RegionId", SmsProperties.REGION_ID);
        request.putQueryParameter("PhoneNumber", "13211111111");
        request.putQueryParameter("SignName", SmsProperties.SIGN_NAME);
        request.putQueryParameter("TemplateCode", "SMS_96695065");


        Gson gson = new Gson();
        String jsonParam = gson.toJson(param);
        //request.putQueryParameter("TemplateParam", "{\"code\":\"123456\"}");
        request.putQueryParameter("TemplateParam",jsonParam);

        try {
            //使用客户端对象携带请求参数向远程阿里云服务器发起远程调用并得到响应结果
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());

            //通信失败的处理
            boolean sucess = response.getHttpResponse().isSuccess();
            Assert.isTrue(sucess, ResponseEnum.ALIYUN_RESPONSE_ERROR);

            //获取响应结果
            String data =response.getData();
            //将返回的json 转为map
            HashMap<String,String> resMap = gson.fromJson(data, HashMap.class);
            String code = resMap.get("Code");
            String message = resMap.get("Message");

            //业务失败的处理
            Assert.equals("OK",code,ResponseEnum.ALIYUN_SMS_ERROR);

        } catch (ServerException e) {
            log.error("阿里云短信发送sdk调用失败："+e.getErrCode()+","+e.getErrMsg());
            throw new BusinessException(ResponseEnum.ALIYUN_SMS_ERROR,e);
        } catch (ClientException e) {
            log.error("阿里云短信发送sdk调用失败："+e.getErrCode()+","+e.getErrMsg());
            throw new BusinessException(ResponseEnum.ALIYUN_SMS_ERROR,e);
        }


    }
}
