package com.example.generator;

import com.example.util.U;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.config.PackageConfig;
import com.mybatisflex.codegen.entity.Table;
import com.mybatisflex.codegen.generator.IGenerator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class RequestGenerator implements IGenerator {

    private String templatePath;

    public RequestGenerator(String templatePath) {
        this.templatePath = templatePath;
    }

    public void generate(Table table, GlobalConfig globalConfig) {
        PackageConfig packageConfig = globalConfig.getPackageConfig();

        String requestClassName = U.getRequestClassName(table);
        String requestPackageName = U.getRequestPackageName();

        String sourceDir = packageConfig.getSourceDir();
        String dataPackagePath = requestPackageName.replace(".", "/");
        File dataJavaFile = new File(sourceDir, dataPackagePath + "/" + requestClassName + globalConfig.getFileType());
        Map<String, Object> params = new HashMap();
        params.put("table", table);
        params.put("packageConfig", packageConfig);
        params.put("javadocConfig", globalConfig.getJavadocConfig());

        // custom
        params.put("requestClassName", requestClassName);
        params.put("requestPackageName", requestPackageName);
        params.putAll(globalConfig.getCustomConfig());
        globalConfig.getTemplateConfig().getTemplate().generate(params, this.templatePath, dataJavaFile);
        System.out.println("Request ---> " + dataJavaFile);
    }

    public String getTemplatePath() {
        return this.templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

}
