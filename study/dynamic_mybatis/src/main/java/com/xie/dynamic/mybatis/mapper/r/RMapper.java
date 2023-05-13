package com.xie.dynamic.mybatis.mapper.r;


import com.xie.dynamic.mybatis.entity.IntegralGrade;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Author:Eric
 * DATE:2023/4/16-15:05
 * Decription:
 */

public interface RMapper {
    @Select("select * from integral_grade ")
    List<IntegralGrade> list();

    @Insert("insert into  integral_grade(`integral_start`,`integral_end`,`borrow_amount`,`is_deleted`) VALUES (#{integralStart},#{integralEnd},#{borrowAmount},#{isDeleted}) ")
    void save (IntegralGrade integralGrade);
}
