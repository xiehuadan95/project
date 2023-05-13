package com.xie.dynamic.mybatis.service.impl;

import com.xie.dynamic.mybatis.entity.IntegralGrade;
import com.xie.dynamic.mybatis.mapper.r.RMapper;
import com.xie.dynamic.mybatis.mapper.w.WMapper;
import com.xie.dynamic.mybatis.service.MyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;


import java.math.BigDecimal;
import java.util.List;

/**
 * Author:Eric
 * DATE:2023/4/16-16:34
 * Decription:
 */
@Service
public class MyServiceImpl implements MyService {
    @Autowired
    private RMapper rMapper;

    @Autowired
    private WMapper wMapper;
    //注入事务template 编程式事务
    @Autowired
   TransactionTemplate wTransactionTemplate;
    @Autowired
    TransactionTemplate rTransactionTemplate;

    //自定义注解声明在方法上 也可以声明在类上 实际过程中一个业务类对应的一个数据源
    @Override
    public List<IntegralGrade> select() {
        return rMapper.list();
    }

    //写
    @Override
    public void save(IntegralGrade vo) {
        wMapper.save(vo);
    }

    //保存写库
    @Override
    public void saveW(IntegralGrade vo) {
        vo.setCName("事务控制写");
        wMapper.save(vo);
    }

    @Override
    public void saveR(IntegralGrade vo) {
        vo.setIntegralStart(200);
        vo.setIntegralEnd(500);
        vo.setBorrowAmount(new BigDecimal(500.00));
        vo.setIsDeleted(0);
        rMapper.save(vo);
    }
  //读 - 写库
//    @Transactional(transactionManager = "wTransactionManager") 可指定事务管理器
//    @Transactional
//    @Override
    public void saveAll1(IntegralGrade vo) {
        saveR(vo);
        saveW(vo);
        int a=1/0;
    }
    //保存读写库 编程式事务来实现不同数据源的事务控制
    @Override
    public void saveAll(IntegralGrade vo) {
//        wTransactionTemplate.execute(new TransactionCallback<Object>() {
//            @Override
//            public Object doInTransaction(TransactionStatus status) {
//                return null;
//            }
//        });
        wTransactionTemplate.execute((wstatus) -> {
            rTransactionTemplate.execute((rstatus)->{
                try {
                    saveR(vo);
                    saveW(vo);
                    int a=1/0;
                } catch (Exception e) {
                 e.printStackTrace();
                 wstatus.setRollbackOnly();
                 rstatus.setRollbackOnly();
                 return false;
                }
                return true;
            });
            return true;
        });

    }


}
