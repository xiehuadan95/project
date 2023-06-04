package com.xie.part2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Author:Eric
 * DATE:2023/6/2-22:43
 * Decription: 复制图片 复制的本质就是从硬盘输入到内存，之后 从内存输出到硬盘
 */
public class CopyFile {
    public static void main(String[] args) throws IOException {
        //1.创建输入输出流
        FileInputStream fis = new FileInputStream("E:\\practiceDemo\\io\\001.jpg");
        FileOutputStream fos = new FileOutputStream("E:\\practiceDemo\\io\\002.jpg");
       //2.一边读取数据一边写出数据  每次读1kb数据
        byte[] buff = new byte[1024];
        int len;//每次有效读取的字节个数
        while((len=fis.read(buff))!=-1){
            fos.write(buff,0,len);
        }

        //关闭输入输出流 先开的 后关
        fos.close();
        fis.close();
    }

}
