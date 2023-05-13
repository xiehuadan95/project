package com.xie.dynamic.mybatis.service;



import com.xie.dynamic.mybatis.entity.IntegralGrade;

import java.util.List;

/**
 * Author:Eric
 * DATE:2023/4/16-16:32
 * Decription:
 */

public interface MyService {
    List<IntegralGrade> select();
    void save(IntegralGrade vo);

    void saveW(IntegralGrade vo);
    void saveR(IntegralGrade vo);
    void saveAll(IntegralGrade vo);
}
