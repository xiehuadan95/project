package com.xie.study.service;

import com.xie.study.entity.IntegralGrade;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:Eric
 * DATE:2023/4/16-16:32
 * Decription:
 */

public interface MyService {
    List<IntegralGrade> select();
    void save(IntegralGrade vo);
}
