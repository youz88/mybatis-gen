package com.example;

import com.example.config.AppConfig;
import com.example.generator.*;
import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.config.TemplateConfig;
import com.mybatisflex.codegen.generator.GeneratorFactory;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Codegen {

    public static void main(String[] args) {
        // 加载配置文件
        loadAppConfig();

        //配置数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(AppConfig.JDBC_URL);
        dataSource.setUsername(AppConfig.JDBC_USERNAME);
        dataSource.setPassword(AppConfig.JDBC_PASSWORD);

        //创建配置内容，两种风格都可以。
        GlobalConfig globalConfig = createGlobalConfigUseStyle1();

        //通过 datasource 和 globalConfig 创建代码生成器
        Generator generator = new Generator(dataSource, globalConfig);

        //生成代码
        generator.generate();
    }

    public static GlobalConfig createGlobalConfigUseStyle1() {
        String modelPackage = AppConfig.BASE_PACKAGE + "." + AppConfig.MODEL;

        // 创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setEntityPackage(modelPackage + ".model");

        // 设置根包
        globalConfig.setBasePackage(modelPackage);

        // 设置表前缀和只生成哪些表
        globalConfig.setTablePrefix(AppConfig.TABLE_PREFIX);
        globalConfig.setGenerateTable(AppConfig.GENERATE_TABLE.split(","));

        // Entity
        globalConfig.setEntityGenerateEnable(true);
        globalConfig.getEntityConfig()
                .setOverwriteEnable(true);

        // Mapper
        globalConfig.setMapperGenerateEnable(true);
        globalConfig.getMapperConfig()
                .setOverwriteEnable(true);

        // Service
        globalConfig.setServiceGenerateEnable(true);
        globalConfig.getMapperConfig()
                .setOverwriteEnable(true);
        globalConfig.setServiceImplGenerateEnable(true);
        globalConfig.getServiceImplConfig()
                .setOverwriteEnable(true);

        // Controller
        globalConfig.setControllerGenerateEnable(true);
        globalConfig.getControllerConfig()
                .setOverwriteEnable(true);

        // 获取模板配置
        TemplateConfig templateConfig = globalConfig.getTemplateConfig();
        templateConfig.setEntity("/templates/entity.tpl");
        templateConfig.setMapper("/templates/mapper.tpl");
        templateConfig.setService("/templates/service.tpl");

        // model
        GeneratorFactory.registerGenerator("controller", new ControllerGenerator("/templates/controller.tpl"));
        GeneratorFactory.registerGenerator("serviceImpl", new ServiceImplGenerator("/templates/serviceImpl.tpl"));
        GeneratorFactory.registerGenerator("data", new DataGenerator("/templates/data.tpl"));
        GeneratorFactory.registerGenerator("dataImpl", new DataImplGenerator("/templates/dataImpl.tpl"));

        // provider
        GeneratorFactory.registerGenerator("interface", new InterfaceGenerator("/templates/interface.tpl"));
        GeneratorFactory.registerGenerator("request", new RequestGenerator("/templates/request.tpl"));
        GeneratorFactory.registerGenerator("response", new ResponseGenerator("/templates/response.tpl"));
        GeneratorFactory.registerGenerator("client", new ClientGenerator("/templates/client.tpl"));

        // web
        GeneratorFactory.registerGenerator("webData", new WebDataGenerator("/templates/webData.tpl"));
        GeneratorFactory.registerGenerator("webDataImpl", new WebDataImplGenerator("/templates/webDataImpl.tpl"));
        GeneratorFactory.registerGenerator("webService", new WebServiceGenerator("/templates/webService.tpl"));
        GeneratorFactory.registerGenerator("webServiceImpl", new WebServiceImplGenerator("/templates/webServiceImpl.tpl"));
        GeneratorFactory.registerGenerator("webController", new WebControllerGenerator("/templates/webController.tpl"));
        GeneratorFactory.registerGenerator("dto", new DTOGenerator("/templates/dto.tpl"));
        GeneratorFactory.registerGenerator("vo", new VOGenerator("/templates/vo.tpl"));
        return globalConfig;
    }

    private static void loadAppConfig() {
        try {
            Properties props = new Properties();
            try (InputStream fis = Codegen.class.getClassLoader().getResourceAsStream("var.properties")) {
                props.load(fis);
                AppConfig.fromProperties(props);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
