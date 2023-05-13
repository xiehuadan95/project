package com.xie.study.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Author:Eric
 * DATE:2023/4/16-14:55
 * Decription: 通用枚举
 */
@Getter
@ToString
@AllArgsConstructor
public enum CommonEnum {
    DATASOURCE_WRITE("W", "写操作数据源"),
    DATASOURCE_READ("R","读操作数据源"),
    ;

    private String dataSourceType;
    private String dataSourceName;
    }
