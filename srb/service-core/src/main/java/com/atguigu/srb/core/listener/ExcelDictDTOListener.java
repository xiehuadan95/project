package com.atguigu.srb.core.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.srb.core.mapper.DictMapper;
import com.atguigu.srb.core.pojo.dto.ExcelDictDTO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:Eric
 * DATE:2023/3/28-22:36
 * Decription: excel监听器
 */
@Slf4j
@NoArgsConstructor
public class ExcelDictDTOListener extends AnalysisEventListener<ExcelDictDTO> {
    //创建临时数据列表 一般数据库执行1000-3000条数据  这里每5条一次
    List<ExcelDictDTO> list = new ArrayList<>();
    //每隔5条记录批量存储一次数据
    private static final int BATCH_COUNT = 5;
    //通过构造函数 注入mapper层 有有参构造函数后 需要一个无参构造函数
    private DictMapper dictMapper;

    public ExcelDictDTOListener(DictMapper mapper) {
        this.dictMapper = mapper;
    }

    //每读一行，执行一次invoke
    @Override
    public void invoke(ExcelDictDTO data, AnalysisContext context) {
        log.info("解析到一条数据：{}", data);
        //每读取到一条数据 先存入数据列表，最后批量存数据库
        list.add(data);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //如果出现最后不足5条的数据 在收尾的时候存储 剩余数据
        saveData();
        log.info("所有数据解析完成！");

    }

    private void saveData() {
        log.info("{}条数据被存储到数据库", list.size());
        //TODO 存数据库
        dictMapper.insertBatch(list);
        log.info("{}条数据存储到数据库成功!", list.size());
    }
}
