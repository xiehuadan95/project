package com.atguigu.srb.core;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

public class CodeGenerator {
    @Test
    public void genCode(){

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("eric");
       //去掉Service接口的首字母I 不加 接口名字前面会有I %s是占位符 实体名字
        gc.setServiceName("%sService");
        //生成后是否打开资源管理器
        gc.setOpen(false);
        //主键策略
        gc.setIdType(IdType.AUTO);
        //开启swagger2模式
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/srb_core?serverTimezone=GMT%2B8&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName();
        pc.setParent("com.atguigu.srb.core");
        //此对象与数据库表结构一一对应，通过dao层向上传输数据源对象
        pc.setEntity("pojo.entity");
//        pc.setService();
//        pc.setXml();
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //数据库表映射到实体的命名策略 数据库表名称 下划线变驼峰
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //数据库表字段映射到实体的命名策略 列名转驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

       // strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        //逻辑删除字段
        strategy.setLogicDeleteFieldName("is_deleted");
        //去掉布尔值的is_前缀（确保tinyint(1) 实体类中最好不要用is做前缀 容易出问题 变成deleted 会加字段映射@TableField
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        //restful风格控制器 一般都会返回json 有@ResponseBody  会直接用@RestController
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);
        //执行
        mpg.execute();

    }
}
