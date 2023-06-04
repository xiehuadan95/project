package com.atguigu.srb.core.controller.api;




import com.atguigu.srb.base.util.JwtUtils;
import com.atguigu.srb.common.result.Result;
import com.atguigu.srb.core.pojo.vo.UserBindVO;
import com.atguigu.srb.core.service.UserBindService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户绑定表 前端控制器
 * </p>
 *
 * @author eric
 * @since 2022-03-19
 */
@Api(tags = "会员账号绑定")
@RestController
@RequestMapping("/api/core/userBind")
@Slf4j
public class UserBindController1 {
    @Resource
    private UserBindService userBindService;

    @ApiOperation("账户绑定提交数据")
    @PostMapping("/auth/bind/")
    public Result bin(@RequestBody UserBindVO userBindVO, HttpServletRequest request) {
        //确保绑定的时候用户已经是登录的了
        //从header中获取token 并对token进行校验 确保用户已登录，因为生成token的时候放了id 此时可以从token中获取userId
        String token = request.getHeader("token");
        Long userId = JwtUtils.getUserId(token);
        //根据userId 进行绑定 最终生成一个动态表单的字符串
//      String formStr =  userBindService.commitBindUser(userBindVO, userId);


//        return R.ok().data("formStr",formStr);
        return null;
    }


}

