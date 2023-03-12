//package com.atguigu.srb.core.controller.admin;
//
//import com.xie.common.result.Result;
//import com.xie.srb.core.pojo.entity.UserLoginRecord;
//import com.xie.srb.core.service.UserLoginRecordService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * Author:Eric
// * DATE:2023/2/26-9:14
// * Decription: 会员登录日志接口
// */
//@Api(tags="会员登录日志接口")
//@RestController
//@RequestMapping("/admin/core/userLoginRecord")
//@Slf4j
//@CrossOrigin //网关层有进行跨域过滤器的配置
//public class AdminUserLoginRecordController {
//
//    @Resource
//    private UserLoginRecordService userLoginRecordService;
//    //历史记录一般只展示部分 最近的日志，没必要查太历史 这样效率高 不用跨服务器查询
//    @ApiOperation("会员登录日志列表")
//    @GetMapping("/listTop50/{userId}")
//    public Result listTop50(
//            @ApiParam(value = "用户id",required = true)
//            @PathVariable Long userId
//    ){
//        List<UserLoginRecord> userLoginRecordList =userLoginRecordService.listTop50(userId);
//
//        return Result.ok().data("list",userLoginRecordList);
//    }
//
//
//}
