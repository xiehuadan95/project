package com.xie.designmode;

/**
 * Author:Eric
 * DATE:2023/6/3-10:55
 * Decription: 装饰器模式 公用接口 基本功能实现类 第一道工序
 */
public class WallBeautifyBase implements WallBeautify{
    @Override
    public void operation() {
        System.out.println("第一步：对墙面进行粉刷清理---");
    }
}
