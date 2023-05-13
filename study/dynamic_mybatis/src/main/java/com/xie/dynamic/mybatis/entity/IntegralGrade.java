package com.xie.dynamic.mybatis.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 积分等级表
 * </p>
 *
 * @author eric
 * @since 2023-03-12
 */
@Data
@ToString
public class IntegralGrade implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    private Integer integralStart;

    private Integer integralEnd;

    private BigDecimal borrowAmount;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private Integer isDeleted;

    private String cName;
    private String cMail;
    private String cPhone;

}
