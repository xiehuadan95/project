package com.atguigu.srb.core.service;

import com.atguigu.srb.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author eric
 * @since 2023-03-12
 */
public interface DictService extends IService<Dict> {

    void importExcel(InputStream inputStream);
}
