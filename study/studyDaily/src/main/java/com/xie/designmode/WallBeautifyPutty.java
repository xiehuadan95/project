package com.xie.designmode;

/**
 * Author:Eric
 * DATE:2023/6/3-11:04
 * Decription: 打腻子 这个方式  继承自装饰角色
 * 此类的作用是装饰的时候打腻子
 */
public class WallBeautifyPutty extends WallBeautifyDecorator{
    //由于继承自 装饰器角色 需要调用父类的构造方法
    public WallBeautifyPutty(WallBeautify wallBeautify) {
        super(wallBeautify);
    }

    @Override
    public void decoration() {
        System.out.println("额外步骤：打腻子");
    }
}
