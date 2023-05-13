package com.xie.study.mapper;

import com.xie.study.entity.IntegralGrade;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Author:Eric
 * DATE:2023/4/16-15:05
 * Decription:
 */
@org.apache.ibatis.annotations.Mapper
public interface Mapper {
    @Select("select * from integral_grade ")
    List<IntegralGrade> list();
    @Insert("insert into  client(`c_name`,`c_mail`,`c_phone`) VALUES (#{cName},#{cMail},#{cPhone}) ")
    void save (IntegralGrade integralGrade);
}
