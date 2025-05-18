package com.example.generator;

import com.example.util.U;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.config.PackageConfig;
import com.mybatisflex.codegen.config.ServiceImplConfig;
import com.mybatisflex.codegen.entity.Table;
import com.mybatisflex.codegen.generator.IGenerator;
import com.mybatisflex.core.util.StringUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class WebServiceImplGenerator implements IGenerator {
    private String templatePath;

    public WebServiceImplGenerator() {
        this("/templates/enjoy/serviceImpl.tpl");
    }

    public WebServiceImplGenerator(String templatePath) {
        this.templatePath = templatePath;
    }

    public void generate(Table table, GlobalConfig globalConfig) {
        PackageConfig packageConfig = globalConfig.getPackageConfig();

        String webServiceImplClassName = table.buildServiceImplClassName();
        String webServicePackageImplName = U.getWebServiceImplPackageName();

        String sourceDir = packageConfig.getSourceDir();
        String dataPackagePath = webServicePackageImplName.replace(".", "/");
        File dataJavaFile = new File(sourceDir, dataPackagePath + "/" + webServiceImplClassName + globalConfig.getFileType());
        Map<String, Object> params = new HashMap<>();
        params.put("table", table);
        params.put("packageConfig", packageConfig);
        params.put("javadocConfig", globalConfig.getJavadocConfig());

        // custom
        params.put("webDataClassName", U.getDataClassName(table));
        params.put("webDataPackageName", U.getWebDataPackageName());
        params.put("webServicePackageName", U.getWebServicePackageName());
        params.put("webServiceImplPackageName", U.getWebServiceImplPackageName());
        params.putAll(globalConfig.getCustomConfig());
        globalConfig.getTemplateConfig().getTemplate().generate(params, this.templatePath, dataJavaFile);
        System.out.println("WebServiceImpl ---> " + dataJavaFile);
    }

    public String getTemplatePath() {
        return this.templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }
}