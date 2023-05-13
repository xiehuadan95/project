package com.xie.study.controler;

//import com.xie.study.DynamicDataSource;
//import com.xie.study.common.CommonEnum;
import com.xie.study.DynamicDataSource;
import com.xie.study.common.CommonEnum;
import com.xie.study.entity.IntegralGrade;
import com.xie.study.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author:Eric
 * DATE:2023/4/16-15:03
 * Decription: 测试控制层
 */
@RestController
@Slf4j
@RequestMapping("/api")
public class MyController {

    @Autowired
    private MyService myService;

    @GetMapping(value = "select")
    public List<IntegralGrade> get(){

        //设置数据源标识为读 通过mybatis插件 或者 aop就不需要手动设置，耦合度变低 用了mybatis插件的方式
//        DynamicDataSource.name.set(CommonEnum.DATASOURCE_READ.getDataSourceType());
        return myService.select();
    }
    @PostMapping(value = "insert")
    public void in(){
        //设置数据源标识为写
//        DynamicDataSource.name.set(CommonEnum.DATASOURCE_WRITE.getDataSourceType());
        IntegralGrade vo = new IntegralGrade();
        vo.setCName("测试3");
        vo.setCMail("69468@qq.com");
        vo.setCPhone("1321904675");
        myService.save(vo);
    }

}
