package com.atguigu.srb.core.controller.admin;


import com.alibaba.excel.EasyExcel;
import com.atguigu.srb.common.exec.BusinessException;
import com.atguigu.srb.common.result.ResponseEnum;
import com.atguigu.srb.common.result.Result;
import com.atguigu.srb.core.pojo.dto.ExcelDictDTO;
import com.atguigu.srb.core.pojo.entity.Dict;
import com.atguigu.srb.core.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@Api(tags = "数据字典管理")
@RestController
@RequestMapping("/admin/core/dict")
@Slf4j
@CrossOrigin
public class AdminDictController {
    @Resource
    DictService dictService;

    @ApiOperation("Excel数据的批量导入")
    @PostMapping("/import")
    public Result batchImport(
            @ApiParam(value = "Excel数据字典文件", required = true)
            @RequestParam("file") MultipartFile file) {

        try {
            InputStream inputStream = file.getInputStream();
            log.info(inputStream.toString());
            dictService.importExcel(inputStream);
            return Result.ok().msg("数据字典批量导入成功");
        } catch (IOException e) {
            log.error("输入流有异常");
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR, e);
        }
    }

    /**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * 1. 创建excel对应的实体对象 参照{@link ExcelDictDTO）
     * 2. 设置返回的 参数
     * 3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
    @ApiOperation("Excel数据的导出")
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("mydict", "UTF-8").replaceAll("\\+", "%20");
        //设置附件的形式下载到本地
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        //写数据列表 data() 可以在业务层创建方法 返回数据列表
        EasyExcel.write(response.getOutputStream(), ExcelDictDTO.class).sheet("数据字典").doWrite(dictService.listDictData());
    }

    /**
     * 延迟加载  根据父类别id加载
     * 不需要后端返回数据中心包含嵌套数据，并且要定义布尔属性hasChildren表示当前节点是否包含子数据
     * 如果hasChildren为true，就表示当前节点包含子数据
     * 如果hasChildren为false，就表示当前节点不包含子数据
     * 如果当前节点包含子数据，那么点击当前节点的时候，就需要通过load方法加载子数据
     */
    @ApiOperation("根据上级id获取子节点数据列表")
    @GetMapping("/listByParentId/{parent}")
    public Result listByParentId(@ApiParam(value = "上级节点id", required = true)
                                 @PathVariable("parent") Long parentId) {
        List<Dict> dictList = dictService.listByParentId(parentId);
        return Result.ok().data("list", dictList);
    }


}
