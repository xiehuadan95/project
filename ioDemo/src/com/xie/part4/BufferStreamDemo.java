package com.xie.part4;

import java.io.*;

/**
 * Author:Eric
 * DATE:2023/6/2-23:27
 * Decription: 字节缓冲流 相同条件下 会比普通流快
 * 内存中有一个内置缓冲区 每次有 8192个字节， 减少IO次数，效率提高
 */
public class BufferStreamDemo {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        //1.创建缓冲流的输入输出对象  创建对象里面需要内嵌一个基础的输入输出流
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("E:\\practiceDemo\\io\\002.jpg"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("E:\\practiceDemo\\io\\002-buff.jpg"));
        //2.读写数据

        int len;
        byte[] buff = new byte[1024];
        while ((len = bis.read(buff)) != -1) {
            //写数据
            bos.write(buff, 0, len);
        }

        bos.close();
        bis.close();
        long end = System.currentTimeMillis();
        System.out.println("花费时间：" + (end - start));

    }
}

