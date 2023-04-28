package com.lantu.xadmin;

import java.util.Collections;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class CodeGenerator {

    public static void main(String[] args) {
        String moduleName="sys";
        String mapperlocation= "C:\\Users\\Lenovo\\typescript\\x-admin\\src\\main\\resources\\mapper\\"+moduleName;
        String table="x_user,x_role,x_menu,x_user_role,x_role_menu";
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/x", "root", "1234")
    .globalConfig(builder -> {
        builder.author("lina") // 设置作者
           // .enableSwagger() // 开启 swagger 模式
           .fileOverride() // 覆盖已生成文件
            .outputDir("C:\\Users\\Lenovo\\typescript\\x-admin\\src\\main\\java"); // 指定输出目录
    })
    .packageConfig(builder -> {
        builder.parent("com.lantu") // 设置父包名
            .moduleName(moduleName) // 设置父包模块名
            .pathInfo(Collections.singletonMap(OutputFile.xml,mapperlocation )); // 设置mapperXml生成路径
    })
    .strategyConfig(builder -> {
        builder.addInclude(table) // 设置需要生成的表名
            .addTablePrefix("x_"); // 设置过滤表前缀
    })
    .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
    .execute();
    }
}
