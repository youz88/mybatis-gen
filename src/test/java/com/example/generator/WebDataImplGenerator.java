package com.example.generator;

import com.example.util.U;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.config.PackageConfig;
import com.mybatisflex.codegen.entity.Table;
import com.mybatisflex.codegen.generator.IGenerator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class WebDataImplGenerator implements IGenerator {

    private String templatePath;

    public WebDataImplGenerator(String templatePath) {
        this.templatePath = templatePath;
    }

    public void generate(Table table, GlobalConfig globalConfig) {
        PackageConfig packageConfig = globalConfig.getPackageConfig();

        String webDataImplClassName = U.getDataImplClassName(table);
        String webDataImplPackageName = U.getWebDataImplPackageName();

        String sourceDir = packageConfig.getSourceDir();
        String dataPackagePath = webDataImplPackageName.replace(".", "/");
        File dataJavaFile = new File(sourceDir, dataPackagePath + "/" + webDataImplClassName + globalConfig.getFileType());
        Map<String, Object> params = new HashMap<>();
        params.put("table", table);
        params.put("packageConfig", packageConfig);
        params.put("javadocConfig", globalConfig.getJavadocConfig());

        // custom
        params.put("webDataImplClassName", webDataImplClassName);
        params.put("webDataImplPackageName", webDataImplPackageName);
        params.put("webDataClassName", U.getDataClassName(table));
        params.put("webDataPackageName", U.getWebDataPackageName());
        params.put("clientClassName", U.getClientClassName(table));
        params.put("clientPackageName", U.getClientPackageName());
        params.putAll(globalConfig.getCustomConfig());
        globalConfig.getTemplateConfig().getTemplate().generate(params, this.templatePath, dataJavaFile);
        System.out.println("WebDataImpl ---> " + dataJavaFile);
    }

    public String getTemplatePath() {
        return this.templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

}
