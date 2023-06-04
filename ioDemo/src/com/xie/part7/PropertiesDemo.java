package com.xie.part7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/**
 * Author:Eric
 * DATE:2023/6/3-10:15
 * Decription: Properties属性
 */
public class PropertiesDemo {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.setProperty("edip","13456");
        properties.setProperty("四川","成都");
        System.out.println(""+properties.getProperty("四川"));
        //遍历
        Set<String> str = properties.stringPropertyNames();
        for (String s : str) {
            String value = properties.getProperty(s);
            System.out.println(s+"值："+value);
        }

        System.out.println("--------------------------------");
        properties.load(new FileInputStream("db.properties"));
        Set<String> keys = properties.stringPropertyNames();
        for (String s : keys) {
            String value = properties.getProperty(s);
            System.out.println(s+"值："+value);
        }

    }
}
