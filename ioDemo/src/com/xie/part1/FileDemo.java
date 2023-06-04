package com.xie.part1;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

/**
 * Author:Eric
 * DATE:2023/6/1-21:08
 * Decription: FILE文件流
 */
public class FileDemo {
    //递归遍历文件夹所有的内容
    public static void printDir(File dir) {
        if (dir.isFile() && dir.getName().endsWith(".java")) {
            //且名字以java结尾
            System.out.println("文件：" + dir);
        } else {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && dir.getName().endsWith(".java")) {
                        //
                        System.out.println(file);
                    } else {
                        printDir(file);
                    }
                }


            }
        }
    }
//使用FileFilter接口
    public static void printDir2(File dir) {


        if (dir.isFile() && dir.getName().endsWith(".java")) {
            //且名字以java结尾
            System.out.println("文件：" + dir);
        } else {
            //只有符合条件的才会放到数组中
            File[] files = dir.listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return file.isDirectory()||file.isFile()&&file.getName().endsWith(".java");
                }
            });
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        printDir2(file);
                    } else {
                        System.out.println(file);
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        //文件相关的操作 代表这个路径下的文件
        File file = new File("E:\\practiceDemo\\io\\demo.txt");

        System.out.println("文件的绝对路径：" + file.getAbsoluteFile());
        System.out.println("文件的名字" + file.getName());
        System.out.println("文件的大小(bytes)" + file.length());
        //判断一下文件是否存在
        if (file.exists()) {
//            file.delete();
        } else {
            file.createNewFile();//创建
        }
        System.out.println("是否是文件：" + file.isFile());
        System.out.println("是否是文件夹：" + file.isDirectory());

        System.out.println("-----------------------------");
        //表示这是个文件夹 也用FILE对象表示
        File dir = new File("E:\\practiceDemo\\io\\test");
        System.out.println("文件夹的绝对路径：" + dir.getAbsoluteFile());
        System.out.println("文件夹的名字：" + dir.getName());
        System.out.println("文件夹的大小(bytes)" + dir.length());  //大小为 0
        if (!dir.exists()) {
            dir.mkdirs();//可以创建多级目录
        } else {
//            dir.delete();//删除
        }
        System.out.println("是否是文件：" + dir.isFile());
        System.out.println("是否是文件夹：" + dir.isDirectory());
        System.out.println("--------文件夹里面的内容 字符串形式打印出来-文件的名字-----------");
        //
        String[] list = dir.list();
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("-------------file对象数组---打印出来绝对路径----");
//        File[] files = dir.listFiles();
//        for (File ele : files) {
//            System.out.println(ele+"是否是文件："+ele.isFile());
//            System.out.println(ele);
//        }
        //遍历目录下所有的内容 包括子目录  递归遍历
//递归遍历
//        printDir(dir);
        printDir2(dir);


    }
}
