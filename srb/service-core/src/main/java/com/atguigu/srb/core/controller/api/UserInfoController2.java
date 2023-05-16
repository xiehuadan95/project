package com.atguigu.srb.core.controller.api;

import com.atguigu.srb.core.service.UserInfoService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;



///**
// * <p>
// * 用户基本信息 前端控制器
// * </p>
// *
// * @author Helen
// * @since 2021-02-20
// */

//@Api(tags = "会员接口")
@RestController
@RequestMapping("/api/core/userInfo")
@Slf4j
@CrossOrigin
public class UserInfoController2 {
    //
//    @Resource
//    private RedisTemplate redisTemplate;
    //    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private UserInfoService userInfoService;

    //
//    @ApiOperation("会员注册")
//    @PostMapping("/register")
//    public Result register(@RequestBody RegisterVO registerVO){
//
//        String mobile = registerVO.getMobile();
//        String password = registerVO.getPassword();
//        String code = registerVO.getCode();
//
//        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
//        Assert.notEmpty(password, ResponseEnum.PASSWORD_NULL_ERROR);
//        Assert.notEmpty(code, ResponseEnum.CODE_NULL_ERROR);
//        Assert.isTrue(RegexValidateUtils.checkCellphone(mobile), ResponseEnum.MOBILE_ERROR);
//
//        //校验验证码是否正确
//        String codeGen = (String)redisTemplate.opsForValue().get("srb:sms:code:" + mobile);
////        String codeGen = redisTemplate.opsForValue().get("srb:sms:code:" + mobile);
//        Assert.equals(code, codeGen, ResponseEnum.CODE_ERROR);
//
//        //注册
//        userInfoService.register(registerVO);
//
//        return Result.ok().msg("注册成功");
//    }
//
//    @ApiOperation("会员登录")
//    @PostMapping("/login")
//    public R login(@RequestBody LoginVO loginVO, HttpServletRequest request){
//
//        String mobile = loginVO.getMobile();
//        String password = loginVO.getPassword();
//
//        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
//        Assert.notEmpty(password, ResponseEnum.PASSWORD_NULL_ERROR);
//
//        String ip = request.getRemoteAddr();
//        UserInfoVO userInfoVO = userInfoService.login(loginVO, ip);
//
//        return R.ok().data("userInfo", userInfoVO);
//    }
//
//    @ApiOperation("校验令牌")
//    @GetMapping("/checkToken")
//    public R checkToken(HttpServletRequest request) {
//
//        String token = request.getHeader("token");
//        boolean result = JwtUtils.checkToken(token);
//
//        if(result){
//            return R.ok();
//        }else{
//            return R.setResult(ResponseEnum.LOGIN_AUTH_ERROR);
//        }
//
//    }
//
    @ApiOperation("校验手机号是否注册")
    @GetMapping("/checkMobile/{mobile}")
    public boolean checkMobile(@PathVariable String mobile) {
        return userInfoService.checkMobile(mobile);
    }
}
//}
//
