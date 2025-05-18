package com.example.generator;

import com.example.util.U;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.config.PackageConfig;
import com.mybatisflex.codegen.config.ServiceConfig;
import com.mybatisflex.codegen.constant.TemplateConst;
import com.mybatisflex.codegen.entity.Table;
import com.mybatisflex.codegen.generator.IGenerator;
import com.mybatisflex.core.util.StringUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class WebServiceGenerator implements IGenerator {

    private String templatePath;

    public WebServiceGenerator() {
        this(TemplateConst.SERVICE);
    }

    public WebServiceGenerator(String templatePath) {
        this.templatePath = templatePath;
    }

    @Override
    public void generate(Table table, GlobalConfig globalConfig) {
        PackageConfig packageConfig = globalConfig.getPackageConfig();

        String webServiceClassName = table.buildServiceClassName();
        String webServicePackageName = U.getWebServicePackageName();

        String sourceDir = packageConfig.getSourceDir();
        String dataPackagePath = webServicePackageName.replace(".", "/");
        File dataJavaFile = new File(sourceDir, dataPackagePath + "/" + webServiceClassName + globalConfig.getFileType());
        Map<String, Object> params = new HashMap();
        params.put("table", table);
        params.put("packageConfig", packageConfig);
        params.put("javadocConfig", globalConfig.getJavadocConfig());

        // custom
        params.put("webServicePackageName", webServicePackageName);
        params.putAll(globalConfig.getCustomConfig());
        globalConfig.getTemplateConfig().getTemplate().generate(params, this.templatePath, dataJavaFile);
        System.out.println("WebService ---> " + dataJavaFile);
    }

    @Override
    public String getTemplatePath() {
        return templatePath;
    }

    @Override
    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

}

