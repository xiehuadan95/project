package com.atguigu.srb.sms.controller.api;

import com.atguigu.srb.common.myassert.Assert;
import com.atguigu.srb.common.result.ResponseEnum;
import com.atguigu.srb.common.result.Result;
import com.atguigu.srb.common.util.RandomUtils;
import com.atguigu.srb.common.util.RegexValidateUtils;
import com.atguigu.srb.sms.service.SmsService;
import com.atguigu.srb.sms.util.SmsProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Author:Eric
 * DATE:2023/5/13-16:34
 * Decription: 短信管理控制层
 */
@RestController
@RequestMapping("/api/sms")
@Api(tags = "短信管理")
@CrossOrigin //跨域
@Slf4j
public class ApiSmsController {
    @Resource
    private SmsService smsService;

    @Resource
    private RedisTemplate redisTemplate;

    @ApiOperation("获取验证码")
    @GetMapping("/send/{mobile}")
    public Result send(
            @ApiParam(value = "手机号",required = true)
            @PathVariable  String mobile){
        //对手机号进行校验，如果不合法没必要发送验证码 进行远程请求占用性能
        //非空
        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        //合法性
        Assert.isTrue(RegexValidateUtils.checkCellphone(mobile),ResponseEnum.MOBILE_ERROR);
        HashMap<String, Object> map = new HashMap<>();
        //生成了一个4位随机数
        String code =RandomUtils.getFourBitRandom();
        map.put("code",code );
        log.info("生成的验证码为：{}",code);

//        smsService.send(mobile, SmsProperties.TEMPLATE_CODE,map);
        //验证码存入redis
        redisTemplate.opsForValue().set("srb:sms:code:"+mobile,code, 1,TimeUnit.MINUTES);

        return Result.ok().msg("短信发送成功");
    }


}
