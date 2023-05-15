package com.atguigu.srb.core.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author:Eric
 * DATE:2023/2/22-22:57
 * Decription: 用户登录后会返回一部分用户信息 封装为一个对象给前端
 */
@Data
@ApiModel(description = "用户信息对象")
public class UserInfoVO {
    @ApiModelProperty(value="用户姓名")
    private String name;
    @ApiModelProperty(value="用户昵称")
    private String nickName;
    @ApiModelProperty(value="头像")
    private String headImg;
    @ApiModelProperty(value="手机")
    private String mobile;
    @ApiModelProperty(value="1：出借人 2：借款人")
    private Integer userType;
    @ApiModelProperty(value="JWT访问令牌")
    private String token;
}
