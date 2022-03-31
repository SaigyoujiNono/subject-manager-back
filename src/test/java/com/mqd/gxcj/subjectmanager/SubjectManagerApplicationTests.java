package com.mqd.gxcj.subjectmanager;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;


@SpringBootTest
class SubjectManagerApplicationTests {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    @Test
    void contextLoads() {
    }

    void createPojo(){
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
                            .fileOverride()
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

    @Test
    void test(){
        LocalDate of = LocalDate.of(2022, 3, 20);
        LocalDate now = LocalDate.now();
        System.out.println(of.compareTo(now));
    }
}
