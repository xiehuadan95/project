package com.atguigu.srb.core.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.srb.core.listener.ExcelDictDTOListener;
import com.atguigu.srb.core.pojo.dto.ExcelDictDTO;
import com.atguigu.srb.core.pojo.entity.Dict;
import com.atguigu.srb.core.mapper.DictMapper;
import com.atguigu.srb.core.service.DictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author eric
 * @since 2023-03-12
 */
@Slf4j
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
    //service层注入mapper  传给监听器
    @Resource
    DictMapper dictMapper;

    //监听器那里一行一行的读取数据，会涉及写入数据库，所以加上事务
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void importExcel(InputStream inputStream) {
        EasyExcel.read(inputStream, ExcelDictDTO.class,new ExcelDictDTOListener(dictMapper)).sheet().doRead();
        log.info("Excel导入完成======");
    }
}
