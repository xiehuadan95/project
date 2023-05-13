package com.atguigu.srb.oss.service;

import java.io.InputStream;

/**
 * Author:Eric
 * DATE:2023/2/20-22:17
 * Decription: 文件上传服务的 接口
 */
public interface FileService {
    /**
     * 文件上传接口
     * @param inputStream  输入流
     * @param module      上传不同类型的文件的模块，不同的目录下
     * @param fileName    文件名字
     * @return
     */
    String upload(InputStream inputStream,String module,String fileName);

    /**
     *
     * @param url
     */
    void removeFile(String url);
}
