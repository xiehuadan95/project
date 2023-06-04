package com.xie.part5;

import java.io.*;

/**
 * Author:Eric
 * DATE:2023/6/2-23:54
 * Decription: 转换流 不同编码格式
 */
public class TransforDemo {
    public static void main(String[] args) throws IOException {
//        FileReader fr = new FileReader("file.txt");
        //可以指定 输入流的编码格式 就不会乱码 输入转换流
        InputStreamReader isr = new InputStreamReader(new FileInputStream("filegbk.txt"),"GBK");
        char[] charArr = new char[100];
        int len;
        while ((len=isr.read(charArr))!=-1){
            System.out.println(new String(charArr,0,len));
        }
        isr.close();
        System.out.println("-------输出转换流---------");

        OutputStreamWriter osw =new OutputStreamWriter(new FileOutputStream("gbk.txt"),"UTF-8");

        osw.write("您好！！\n");
        osw.write("转换流");
        osw.close();

    }
}
