package com.xie;

import com.xie.designmode.*;

/**
 * Author:Eric
 * DATE:2023/6/3-11:11
 * Decription: 装饰器模式 具体使用演示
 */
public class DecoratorTest {
    public static void main(String[] args) {
        //正常步骤 清理墙面-打腻子-涂漆-演示展示
        WallBeautifyBase clean = new WallBeautifyBase();
        clean.operation();
        System.out.println("-----墙面清理一个步骤----");
        WallBeautifyPutty putty = new WallBeautifyPutty(clean);
        putty.operation();
        System.out.println("-----墙面清理---打腻子-----");
        WallBeautifyPaint paint = new WallBeautifyPaint(putty);
        paint.operation();
        System.out.println("-----墙面清理---打腻子--涂油漆---");
        WallBeautifyPPT ppt = new WallBeautifyPPT(paint);
        //整体做的
        ppt.operation();
        //这个步骤具体做的
        ppt.decoration();
        System.out.println("-----墙面清理---打腻子--涂油漆-演示--");
        System.out.println("整合在一起，步骤可以打乱可多层嵌套============");
        WallBeautify wbh = new WallBeautifyPPT(new WallBeautifyBase());
        wbh.operation();
        WallBeautify wbh2 = new WallBeautifyPaint(new WallBeautifyPPT(new WallBeautifyBase()));
        wbh2.operation();

    }
}
