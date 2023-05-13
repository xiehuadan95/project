package com.xie.dynamic.mybatis.mapper.w;


import com.xie.dynamic.mybatis.entity.IntegralGrade;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Author:Eric
 * DATE:2023/4/16-15:05
 * Decription:
 */

public interface WMapper {
    @Select("select * from integral_grade ")
    List<IntegralGrade> list();


    @Insert("insert into  client(`c_name`,`c_mail`,`c_phone`) VALUES (#{cName},#{cMail},#{cPhone}) ")
    void save (IntegralGrade integralGrade);
}
