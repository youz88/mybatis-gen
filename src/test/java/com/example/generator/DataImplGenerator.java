package com.example.generator;

import com.example.util.U;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.config.PackageConfig;
import com.mybatisflex.codegen.entity.Table;
import com.mybatisflex.codegen.generator.IGenerator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DataImplGenerator implements IGenerator {

    private String templatePath;

    public DataImplGenerator(String templatePath) {
        this.templatePath = templatePath;
    }

    public void generate(Table table, GlobalConfig globalConfig) {
        PackageConfig packageConfig = globalConfig.getPackageConfig();

        String dataImplClassName = U.getDataImplClassName(table);
        String dataImplPackageName = U.getDataImplPackageName();

        String sourceDir = packageConfig.getSourceDir();
        String dataPackagePath = dataImplPackageName.replace(".", "/");
        File dataJavaFile = new File(sourceDir, dataPackagePath + "/" + dataImplClassName + globalConfig.getFileType());
        Map<String, Object> params = new HashMap<>();
        params.put("table", table);
        params.put("packageConfig", packageConfig);
        params.put("javadocConfig", globalConfig.getJavadocConfig());

        // custom
        params.put("dataImplClassName", dataImplClassName);
        params.put("dataImplPackageName", dataImplPackageName);
        params.put("dataClassName", U.getDataClassName(table));
        params.put("dataPackageName", U.getDataPackageName());
        params.putAll(globalConfig.getCustomConfig());
        globalConfig.getTemplateConfig().getTemplate().generate(params, this.templatePath, dataJavaFile);
        System.out.println("DataImpl ---> " + dataJavaFile);
    }

    public String getTemplatePath() {
        return this.templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

}
