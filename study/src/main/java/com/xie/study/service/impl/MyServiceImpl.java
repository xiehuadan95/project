package com.xie.study.service.impl;

import com.xie.study.annotion.WR;
import com.xie.study.entity.IntegralGrade;
import com.xie.study.mapper.Mapper;
import com.xie.study.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author:Eric
 * DATE:2023/4/16-16:34
 * Decription:
 */
@Service
public class MyServiceImpl implements MyService {
    @Autowired
    Mapper mapper;
    //自定义注解声明在方法上 也可以声明在类上 实际过程中一个业务类对应的一个数据源
    @Override
//    @WR("R")
    public List<IntegralGrade> select() {

        return mapper.list();
    }

    @Override
//    @WR("W")
    public void save(IntegralGrade vo) {
        mapper.save(vo);
    }
}
