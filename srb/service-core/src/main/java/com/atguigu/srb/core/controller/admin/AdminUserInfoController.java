//package com.atguigu.srb.core.controller.admin;
//
//
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.xie.common.result.Result;
//import com.xie.srb.core.pojo.entity.UserInfo;
//import com.xie.srb.core.pojo.query.UserInfoQuery;
//import com.xie.srb.core.service.UserInfoService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//
///**
// * <p>
// * 用户基本信息 前端控制器
// * </p>
// *
// * @author eric
// * @since 2022-03-19
// */
//@Api(tags = "会员管理")
//@RestController
//@RequestMapping("/admin/core/userInfo")
//@Slf4j
//@CrossOrigin
//public class AdminUserInfoController {
//
//
//    @Resource
//    private UserInfoService userInfoService;
//
//    //会员注册的方法  get是没法获取 requestBody的
//    @ApiOperation("获取会员分页列表")
//    @GetMapping("/list/{page}/{limit}")
//    public Result listPage(
//            @ApiParam(value = "当前页码", required = true)
//            @PathVariable Long page,
//            @ApiParam(value = "每页记录数", required = true)
//            @PathVariable long limit,
//            @ApiParam(value = "查询对象", required = false) //可以什么条件也没有
//                    UserInfoQuery userInfoQuery
//    ) {
//        //分页信息  + 查询条件查
//        Page<UserInfo> pageParam = new Page<>();
//        //返回一个分页模型对象 里面有分页信息 当前页的所有记录列表 一共有多少记录 有没有下一页
//        IPage<UserInfo> pageModel = userInfoService.listPage(pageParam, userInfoQuery);
//        return Result.ok().data("pageModel", pageModel);
//
//    }
//
//    @ApiOperation("锁定和解锁，根据用户id去更新用户的状态，状态由前端传入")
//    @PutMapping("/lock/{id}/{status}")
//    public Result lock(
//            @ApiParam(value = "用户id", required = true)
//            @PathVariable("id") Long id,
//            @ApiParam(value = "锁定状态", required = true)
//            @PathVariable("status") Integer status
//    ) {
//        userInfoService.lock(id, status);
//        return Result.ok().msg(status == 1 ? "解锁成功" : "锁定成功");
//    }
//
//}
//
