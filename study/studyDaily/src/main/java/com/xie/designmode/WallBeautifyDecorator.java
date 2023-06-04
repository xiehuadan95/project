package com.xie.designmode;

/**
 * Author:Eric
 * DATE:2023/6/3-10:57
 * Decription: 装饰器模式 装饰器角色类  抽象类 实现并持有一个 WallBeautify的具体对象实例，这里使用聚合而不是继承
 */
public abstract class WallBeautifyDecorator implements WallBeautify {
    //持有一个WallBeautify的对象实例
    private WallBeautify wallBeautify;

    //有参构造 需要将实例放入构造方法 每次使用装饰器角色的时候 都需要引入这个基础的实现类
    public WallBeautifyDecorator(WallBeautify wallBeautify) {
        this.wallBeautify = wallBeautify;
    }

    //由于实现了公用的基本接口 需要重写方法，后续 此类的实例调用operation方法的时候都需要 先调用 基本实例的基础方法，也就是装饰过程中 前面第一步是必要的
    //并在基础的方法里面，调用扩展的步骤方法，这样每次有扩展都会在原有基础上再进行扩展调用
    @Override
    public void operation() {
        wallBeautify.operation();
        decoration();
    }
    //扩展 自定义需要装饰的方法 抽象方法，实例去完成
    public abstract void decoration();
}
