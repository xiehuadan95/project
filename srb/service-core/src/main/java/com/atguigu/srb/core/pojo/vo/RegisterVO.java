package com.atguigu.srb.core.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author:Eric
 * DATE:2023/2/22-21:20
 * Decription: 前端传过来的vo对象，在后端并没有相应的表 或者对象来存
 */
@Data
@ApiModel(description = "注册对象")
public class RegisterVO {

    @ApiModelProperty(value = "用户类型")
    private  Integer userType;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "验证码")
    private String code;
    @ApiModelProperty(value = "密码")
    private String password;

}
