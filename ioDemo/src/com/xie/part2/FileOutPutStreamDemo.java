package com.xie.part2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:Eric
 * DATE:2023/6/1-22:57
 * Decription:
 */
public class FileOutPutStreamDemo {
    public static void main(String[] args) {
        List list = new ArrayList(10);


        for (int i = 0; i < 10; i++) {
            StringBuffer buffer = new StringBuffer("1305684682");
            buffer.append(i).append("||您有一条数据需要处理\n");
            list.add(buffer.toString().getBytes());
        }
        System.out.println();
        FileOutputStream fos = null;
        //1.创建字节输出流
        try {
//            fos = new FileOutputStream("E:\\practiceDemo\\io\\fileDemo.txt",true); 如果有true 就是追加操作，第二次执行会在原来的基础上 追加内容，而不是覆盖之前的内容
            fos = new FileOutputStream("E:\\practiceDemo\\io\\fileDemo.txt");
            //2.写出数据 从内存写到硬盘上 一个字节一个字节的写 虽然参数为int类型 4个字节，但是只会保留一个字节的信息写出
//            fos.write(97); //a
//            fos.write(98); //b
//            fos.write(99); //c
//可写出一个字节数组
            if(null!=list && list.size()>0){
                for (int i = 0; i < list.size(); i++) {
                    fos.write((byte[]) list.get(i));
                }
            }
            //也可以写出一个字节数组 指定区间
            byte[] byte1 = "sdfjksdlfj".getBytes("gbk");
            fos.write(byte1,4,3); //从下标4 开始写3位
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //3.最后关闭输出流
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
