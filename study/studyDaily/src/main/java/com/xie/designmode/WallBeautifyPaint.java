package com.xie.designmode;

/**
 * Author:Eric
 * DATE:2023/6/3-11:07
 * Decription: 装饰的时候涂油漆
 */
public class WallBeautifyPaint extends WallBeautifyDecorator {

    public WallBeautifyPaint(WallBeautify wallBeautify) {
        super(wallBeautify);
    }

    @Override
    public void decoration() {
        System.out.println("装饰步骤：涂油漆");
    }
}
