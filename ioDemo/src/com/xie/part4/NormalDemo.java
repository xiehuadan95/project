package com.xie.part4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Author:Eric
 * DATE:2023/6/2-23:30
 * Decription: 普通流来复制
 */
public class NormalDemo {
    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();
        //1.创建输入输出流
        FileInputStream fis = new FileInputStream("E:\\practiceDemo\\io\\002.jpg");
        FileOutputStream fos = new FileOutputStream("E:\\practiceDemo\\io\\002-bak.jpg");
        //2.读写数据

        int len;
        byte[] buff=new byte[1024];
        while ((len=fis.read(buff))!=-1){
            //写数据
            fos.write(buff,0,len);
        }

        fos.close();
        fis.close();
        long end = System.currentTimeMillis();
        System.out.println("花费时间："+(end-start));

    }
}
