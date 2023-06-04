package com.xie.part4;

import java.io.*;

/**
 * Author:Eric
 * DATE:2023/6/2-23:43
 * Decription: 字符缓冲流 可以读写一行
 */
public class BufferStreamDemo2 {
    public static void main(String[] args) throws IOException {
        //1.创建字符缓冲输入流对象
        BufferedReader br = new BufferedReader(new FileReader("read.txt"));
        //2.读取数据
        //新增的读取一行
//        String line = br.readLine();
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("line:" + line);
        }
//关闭资源
        br.close();
        System.out.println("===================");
        //创建字符缓冲输出流对象
        BufferedWriter bw = new BufferedWriter(new FileWriter("writerBuffer.txt"));
        //写数据
        bw.write("hello");
        bw.newLine(); //换行
        bw.write("china");
//关闭资源
        bw.close();
    }
}
