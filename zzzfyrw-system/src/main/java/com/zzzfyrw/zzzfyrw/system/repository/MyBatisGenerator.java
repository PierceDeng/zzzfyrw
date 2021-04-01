package com.zzzfyrw.zzzfyrw.system.repository;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;

public class MyBatisGenerator {

    public static void main(String[] args) {
        AutoGenerator generator = new AutoGenerator();

        GlobalConfig config = new GlobalConfig();
        config.setAuthor("dpz");
        config.setEnableCache(true);
        config.setOutputDir(System.getProperty("user.dir")
                + File.separator+"zzzfyrw-system"+File.separator+"src"
                + File.separator+"main"+File.separator+"java");
        config.setEntityName("%sEntity");
        config.setMapperName("%sMapper");
        config.setXmlName("%sMapper");
        config.setServiceName("%sService");
        config.setIdType(IdType.ASSIGN_ID); //雪花id
        config.setOpen(false);
        config.setBaseColumnList(true);
        config.setBaseResultMap(true);

        DataSourceConfig source = new DataSourceConfig();
        source.setUrl("jdbc:mysql://127.0.0.1:3306/zzzfyrw?useUnicode=true" +
                "&characterEncoding=utf8&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8");
        source.setDriverName("com.mysql.cj.jdbc.Driver");
        source.setUsername("root");
        source.setPassword("root");
        source.setDbType(DbType.MYSQL);

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.zzzfyrw.system");
        packageConfig.setEntity("repository.entity");
        packageConfig.setMapper("repository.mapper");
        packageConfig.setXml("repository.xml");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");

        StrategyConfig strategy = new StrategyConfig();
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setInclude("sys_user","sys_role_user","sys_role","sys_role_permission","sys_permission");//表名
        strategy.setRestControllerStyle(true);
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setEntityLombokModel(Boolean.TRUE);

        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                ;
            }
        };

        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController(null);
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
//        templateConfig.setMapper(null);
//        templateConfig.setXml(null);
        config.setFileOverride(true);

        generator.setGlobalConfig(config);
        generator.setPackageInfo(packageConfig);
        generator.setDataSource(source);
        generator.setStrategy(strategy);
        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        generator.setCfg(injectionConfig);
        generator.setTemplate(templateConfig);


        generator.execute();
    }

}
