package com.atguigu.srb.core.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author:Eric
 * DATE:2023/2/25-0:00
 * Decription: 前端查询返回的 查询对象
 */
@Data
@ApiModel(description = "会员搜索对象")
public class UserInfoQuery {
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value="状态")
    private Integer status;
    @ApiModelProperty(value = "1:出借人 2：借款人")
    private Integer userType;
}
