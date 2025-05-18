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

public class ServiceImplGenerator implements IGenerator {
    private String templatePath;

    public ServiceImplGenerator() {
        this("/templates/enjoy/serviceImpl.tpl");
    }

    public ServiceImplGenerator(String templatePath) {
        this.templatePath = templatePath;
    }

    public void generate(Table table, GlobalConfig globalConfig) {
        if (globalConfig.isServiceImplGenerateEnable()) {
            PackageConfig packageConfig = globalConfig.getPackageConfig();
            ServiceImplConfig serviceImplConfig = globalConfig.getServiceImplConfig();
            String sourceDir = StringUtil.hasText(serviceImplConfig.getSourceDir()) ? serviceImplConfig.getSourceDir() : packageConfig.getSourceDir();
            String serviceImplPackagePath = packageConfig.getServiceImplPackage().replace(".", "/");
            File serviceImplJavaFile = new File(sourceDir, serviceImplPackagePath + "/" + table.buildServiceImplClassName() + globalConfig.getFileType());
            if (!serviceImplJavaFile.exists() || serviceImplConfig.isOverwriteEnable()) {
                Map<String, Object> params = new HashMap(4);
                params.put("table", table);
                params.put("packageConfig", packageConfig);
                params.put("serviceImplConfig", serviceImplConfig);
                params.put("javadocConfig", globalConfig.getJavadocConfig());

                // custom
                params.put("dataClassName", U.getDataClassName(table));
                params.put("dataPackageName", U.getDataPackageName());
                params.putAll(globalConfig.getCustomConfig());
                globalConfig.getTemplateConfig().getTemplate().generate(params, this.templatePath, serviceImplJavaFile);
                System.out.println("ServiceImpl ---> " + serviceImplJavaFile);
            }
        }
    }

    public String getTemplatePath() {
        return this.templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }
}