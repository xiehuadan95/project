package com.xie.designmode;

/**
 * Author:Eric
 * DATE:2023/6/3-11:09
 * Decription: 装饰器模式下 这个装饰角色 需要演示 扩展
 */
public class WallBeautifyPPT extends WallBeautifyDecorator{
    public WallBeautifyPPT(WallBeautify wallBeautify) {
        super(wallBeautify);
    }

    @Override
    public void decoration() {
        System.out.println("给客人演示扩展步骤===");
    }
}
