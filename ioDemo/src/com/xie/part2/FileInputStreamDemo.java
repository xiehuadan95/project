package com.xie.part2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Author:Eric
 * DATE:2023/6/2-22:24
 * Decription:
 */
public class FileInputStreamDemo {
    public static void main(String[] args) throws IOException {
        //创建输入流对象 相对路径 会在项目根路径找此文件
        FileInputStream fis = new FileInputStream("fis.txt");
        //读取数据 一个字符一个字符的读
        /*char a = (char)fis.read();
        System.out.println("一个字符："+a);
        a=(char)fis.read();
        System.out.println("下一个字符："+a);
        //文件末尾会返回-1
        int end=fis.read();
        System.out.println("文件末尾是："+end);*/

        //一个字节一个字节的读 转为字符输出
        int c;
       while ((c=fis.read())!=-1){
           System.out.println("字符："+(char)c);
       }
       fis.close();
        FileInputStream fisf = new FileInputStream("fisf.txt");
       //读取一个字节数组 一次读两个
        byte[] bytes=new byte[2];
        int len; //每次读取到的有效字节个数

        while ((len=fisf.read(bytes))!=-1){
            //到最后的时候是 e,此时每次读2个字节长度会往回读一个
//            System.out.println("字符串"+new String(bytes));
            System.out.println("长度："+len);
            System.out.println("字符串："+new String(bytes,0,len));
        }
        //关闭流
        fisf.close();


    }
}
