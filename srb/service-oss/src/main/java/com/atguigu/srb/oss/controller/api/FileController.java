package com.atguigu.srb.oss.controller.api;

import com.atguigu.srb.common.exec.BusinessException;
import com.atguigu.srb.common.result.ResponseEnum;
import com.atguigu.srb.common.result.Result;
import com.atguigu.srb.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * Author:Eric
 * DATE:2023/2/20-22:43
 * Decription: 阿里云文件管理
 */
@Api(tags = "阿里云文件管理")
@CrossOrigin
@RestController
@RequestMapping("/api/oss/file")
public class FileController {

    @Resource
    private FileService fileService;

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public Result upload(
            @ApiParam(value = "文件", required = true)
            @RequestParam("file") MultipartFile file,
            @ApiParam(value = "模块", required = true)
            @RequestParam("module") String module

    ) {
        try {
            InputStream inputStream = file.getInputStream();
            //原始的文件名字
            String originalFilename = file.getOriginalFilename();
            //返回了一个上传的地址
            String url="上传的地址";
//            String url = fileService.upload(inputStream, module, originalFilename);
            return Result.ok().msg("文件上传成功").data("url", url);
        } catch (IOException e) {
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR, e);
        }
    }

    @ApiOperation("oss删除文件")
    @DeleteMapping("/remove")
    public Result remove(@ApiParam(value = "要删除的文件",required = true)
                             @RequestParam("url") String url){
                fileService.removeFile(url);
        return Result.ok().msg("删除成功");
    }


}
