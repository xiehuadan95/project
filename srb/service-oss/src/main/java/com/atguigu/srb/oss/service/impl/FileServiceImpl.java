package com.atguigu.srb.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.atguigu.srb.oss.service.FileService;
import com.atguigu.srb.oss.util.OssProperties;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

/**
 * Author:Eric
 * DATE:2023/2/20-22:18
 * Decription: 文件上传服务的实现
 */
@Service
public class FileServiceImpl implements FileService {

    /**
     * 文件上传 上传文件流
     * @param inputStream  输入流
     * @param module      上传不同类型的文件的模块，不同的目录下
     * @param fileName    文件名字
     * @return
     */
    @Override
    public String upload(InputStream inputStream, String module, String fileName) {

        //创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(OssProperties.ENDPOINT, OssProperties.KEY_ID, OssProperties.KEY_SECRET);
        //判断BUCKET_NAME是否存在 如果不存在则创建
        if(!ossClient.doesBucketExist(OssProperties.BUCKET_NAME)){
            ossClient.createBucket(OssProperties.BUCKET_NAME);
            //设置权限为读写权限
            ossClient.setBucketAcl(OssProperties.BUCKET_NAME, CannedAccessControlList.PublicRead);
        }
        //文件目录结构 “test/2023/02/20/uuid.**”
        //构建日期路径
        String tiemFolder = new DateTime().toString("/yyyy/MM/dd/");
        //文件名生成
        fileName=UUID.randomUUID()+fileName.substring(fileName.lastIndexOf("."));

        String path=module+tiemFolder+fileName;
        //上传文件流
//        FileInputStream inputStream1 = new FileInputStream();
        ossClient.putObject(OssProperties.BUCKET_NAME,path,inputStream);
        //关闭OSSClient
        ossClient.shutdown();
        //文件的url地址

        return "https://"+OssProperties.BUCKET_NAME+"."+OssProperties.ENDPOINT+"/"+path;
    }

    @Override
    public void removeFile(String url) {

    }
}
