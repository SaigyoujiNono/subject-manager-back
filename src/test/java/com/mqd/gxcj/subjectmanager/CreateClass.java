package com.mqd.gxcj.subjectmanager;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

public class CreateClass {
    public static void main(String[] args) {
        String url = "jdbc:mysql://192.168.196.195:3306/sub_manager?characterEncoding=utf8&useSSL=true&serverTimezone=GMT%2B8";
        String username = "mqd";
        String password = "eYxQNWz2gTKgwId&";
        FastAutoGenerator.create(new DataSourceConfig.Builder(url, username, password))
                .globalConfig(builder -> {
                    builder.outputDir("F:\\intellij_project\\subject_manager\\src\\main\\java")
                            .author("莫桥德")
                            .enableSwagger()
                            .dateType(DateType.TIME_PACK)
                            .build();
                })
                .packageConfig(builder -> {
                    builder.parent("com.mqd.gxcj.subjectmanager")
                            .entity("pojo")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .xml("mapper.xml")
                            .controller("controller")
                            .build();
                })
                .strategyConfig(builder -> {
                    builder.addTablePrefix("s_","p_")
                            .entityBuilder()
                            .enableLombok()
//                            .fileOverride()
                            .enableChainModel()
                            .controllerBuilder()
                            .enableRestStyle()
                            .formatFileName("%sController")
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .mapperBuilder()
                            .formatMapperFileName("%sMapper")
                            .formatXmlFileName("%sMapper")
                            .build();
                }).templateEngine(new VelocityTemplateEngine())
                .execute();

    }
}
