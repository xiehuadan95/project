//package com.atguigu.srb.core.controller.api;
//
//
//import com.xie.common.exception.Assert;
//import com.xie.common.result.ResponseEnum;
//import com.xie.common.result.Result;
//import com.xie.common.util.RegexValidateUtils;
//import com.xie.srb.base.util.JwtUtils;
//import com.xie.srb.core.pojo.vo.LoginVO;
//import com.xie.srb.core.pojo.vo.RegisterVO;
//import com.xie.srb.core.pojo.vo.UserInfoVO;
//import com.xie.srb.core.service.UserInfoService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
///**
// * <p>
// * 用户基本信息 前端控制器
// * </p>
// *
// * @author eric
// * @since 2022-03-19
// */
//@Api(tags="会员接口")
//@RestController
//@RequestMapping("/api/core/userInfo")
//@Slf4j
//@CrossOrigin
//public class UserInfoController {
//
//    @Resource
//    private RedisTemplate redisTemplate;
////    @Resource
////    private RedisTemplate<String,String> redisTemplate;
//    @Resource
//    private UserInfoService userInfoService;
//
////会员注册的方法
//    @ApiOperation("会员注册")
//    @PostMapping("/register")
//    public Result register(@RequestBody RegisterVO registerVO){
//        //防止恶意攻击绕过前端，后端代码做校验
//        String mobile =registerVO.getMobile();
//        String password =registerVO.getPassword();
//        String code = registerVO.getCode();
//        //校验
//        Assert.notEmpty(mobile,ResponseEnum.MOBILE_NULL_ERROR);
//        Assert.notEmpty(password,ResponseEnum.PASSWORD_NULL_ERROR);
//        Assert.notEmpty(code,ResponseEnum.CODE_NULL_ERROR);
//        Assert.isTrue(RegexValidateUtils.checkCellphone(mobile),ResponseEnum.MOBILE_ERROR);
//
//        //从redis中取 不能用泛型返回串，因为存的时候存的是json串 是带双引号的"\"123\""，而串 是没有双引号的"123"，
//        //直接取出对象再去转，会获取到具体内容。而用泛型获取出来 会得到 \" \"带双引号的内容
//        String codeGet = (String)redisTemplate.opsForValue().get("srb:sms:code:" + mobile);
////        String code1 = redisTemplate.opsForValue().get("srb:sms:code:" + mobile);
//        log.info("redis获取到的验证码为:{}",codeGet);
//        Assert.equals(code,codeGet, ResponseEnum.CODE_ERROR);
//        //注册
//        userInfoService.register(registerVO);
//        return  Result.ok().msg("注册成功");
//
//    }
//    @ApiOperation("会员登录")
//    @PostMapping("/login")
//    public Result login(@RequestBody LoginVO loginVO, HttpServletRequest request){
//        String mobile =loginVO.getMobile();
//        String password =loginVO.getPassword();
//        Assert.notEmpty(mobile,ResponseEnum.MOBILE_NULL_ERROR);
//        Assert.notEmpty(password,ResponseEnum.PASSWORD_NULL_ERROR);
//
//        String ip =request.getRemoteAddr();
//        UserInfoVO userInfoVO = userInfoService.login(loginVO,ip);
//        return Result.ok().data("userInfo",userInfoVO);
//    }
//    //token放在请求头中 过来
//    @ApiOperation("校验令牌")
//    @GetMapping("/checkToken")
//    public Result checkToken(HttpServletRequest request){
//
//        String token = request.getHeader("token");
//        boolean res = JwtUtils.checkToken(token);
//        if(res){
//            return Result.ok();
//        }else {
//            return Result.setResult(ResponseEnum.LOGIN_LOKED_ERROR);
//        }
//    }
//
//    //
//    @ApiOperation("校验手机号是否注册")
//    @GetMapping("/checkMobile/{mobile}")
//    public Boolean checkMobile(@PathVariable String mobile){
////        boolean res = userInfoService.checkMobile(mobile);
////        return Result.ok().data("isExist",res);
//        return userInfoService.checkMobile(mobile);
//    }
//
//}
//
