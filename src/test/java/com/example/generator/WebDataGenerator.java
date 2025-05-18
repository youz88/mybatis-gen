package com.example.generator;

import com.example.util.U;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.config.PackageConfig;
import com.mybatisflex.codegen.entity.Table;
import com.mybatisflex.codegen.generator.IGenerator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class WebDataGenerator implements IGenerator {

    private String templatePath;

    public WebDataGenerator(String templatePath) {
        this.templatePath = templatePath;
    }

    public void generate(Table table, GlobalConfig globalConfig) {
        PackageConfig packageConfig = globalConfig.getPackageConfig();

        String webDataClassName = U.getDataClassName(table);
        String webDataPackageName = U.getWebDataPackageName();

        String sourceDir = packageConfig.getSourceDir();
        String dataPackagePath = webDataPackageName.replace(".", "/");
        File dataJavaFile = new File(sourceDir, dataPackagePath + "/" + webDataClassName + globalConfig.getFileType());
        Map<String, Object> params = new HashMap();
        params.put("table", table);
        params.put("packageConfig", packageConfig);
        params.put("javadocConfig", globalConfig.getJavadocConfig());

        // custom
        params.put("webDataClassName", webDataClassName);
        params.put("webDataPackageName", webDataPackageName);
        params.putAll(globalConfig.getCustomConfig());
        globalConfig.getTemplateConfig().getTemplate().generate(params, this.templatePath, dataJavaFile);
        System.out.println("WebData ---> " + dataJavaFile);
    }

    public String getTemplatePath() {
        return this.templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

}
