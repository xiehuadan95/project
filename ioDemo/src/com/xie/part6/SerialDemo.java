package com.xie.part6;

import java.io.*;

/**
 * Author:Eric
 * DATE:2023/6/3-8:20
 * Decription: 序列化  内存 →磁盘   对象必须实现Serializable接口 (只是一个标识 标识这个类是可以被序列化的)
 * 必须保证所有的属性均可序列化 ，（transient 修饰为临时属性，不参与序列化
 */
public class SerialDemo {
    public static void main(String[] args) throws IOException {
        //创建对象的输出流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Employee.txt"));
        //创建对象
        Employee employee=new Employee("逍遥子","杭州");
        //序列化   对象的信息二进制的形式写进去
        oos.writeObject(employee);


        oos.close();
        System.out.println("序列化完成");

    }
}
class Employee implements Serializable {
    private static final long serialVersionUID = 1l;
    private String name;
    private transient String address;  //transient修饰的属性不能被序列化 是瞬时状态


    public Employee() {

    }

    public Employee(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
