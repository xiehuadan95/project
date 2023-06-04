package com.xie.part6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Author:Eric
 * DATE:2023/6/3-8:41
 * Decription: 反序列化
 */
public class DeSerialDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //创建对象的输入流对象
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Employee.txt"));
        //读取数据并且恢复对象
        Employee employee = (Employee)ois.readObject();
        //
        System.out.println(employee);

        ois.close();
        System.out.println("反序列化完成===");
    }
}
