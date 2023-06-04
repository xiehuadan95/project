package com.xie.part3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:Eric
 * DATE:2023/6/2-23:14
 * Decription: 字符输出流
 */
public class FileWriterDemo {
    public static void main(String[] args) throws IOException {
        List list = new ArrayList(10);

        for (int i = 0; i < 10; i++) {
            StringBuffer buffer = new StringBuffer("1305684682");
            buffer.append(i).append("||您有一条数据需要处理\n");
            list.add(buffer.toString().toCharArray());
        }

        //创建一个字符输出流对象
        FileWriter fw = new FileWriter("writer.txt");


        //写出数据
   /*     fw.write(97);
        fw.write(98);
        fw.write(99);
        fw.write(100);*/

        //字符串转为字符数组，写出字符数组
        if (null != list && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                fw.write((char[]) list.get(i));
            }
        }
//直接写字符串
        fw.write("我爱我家！\n");
        //关闭
//        fw.close();
        //关闭了以后有想继续写 不能用close 可以用刷新 刷新到文件
        fw.flush();
        fw.write("lala");
        fw.close();


    }
}
