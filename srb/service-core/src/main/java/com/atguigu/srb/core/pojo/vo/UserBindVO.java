package com.atguigu.srb.core.pojo.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Author:Eric
 * DATE:2023/2/28-23:02
 * Decription: 绑定用户到第三方资金监管账户的vo对象
 */
@Data
@ApiModel(description = "账户绑定")
public class UserBindVO {
    //身份证号
    private String idCard;
    //用户姓名
    private String name;
    //银行类型
    private String bankType;
    //银行卡号
    private String bankNo;
    //手机号
    private String mobile;
}
