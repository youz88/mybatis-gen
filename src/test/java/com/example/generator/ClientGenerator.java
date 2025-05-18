package com.example.generator;

import com.example.config.AppConfig;
import com.example.util.U;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.config.PackageConfig;
import com.mybatisflex.codegen.entity.Table;
import com.mybatisflex.codegen.generator.IGenerator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ClientGenerator implements IGenerator {

    private String templatePath;

    public ClientGenerator(String templatePath) {
        this.templatePath = templatePath;
    }

    public void generate(Table table, GlobalConfig globalConfig) {
        PackageConfig packageConfig = globalConfig.getPackageConfig();

        String clientClassName = U.getClientClassName(table);
        String clientPackageName = U.getClientPackageName();

        String sourceDir = packageConfig.getSourceDir();
        String dataPackagePath = clientPackageName.replace(".", "/");
        File dataJavaFile = new File(sourceDir, dataPackagePath + "/" + clientClassName + globalConfig.getFileType());
        Map<String, Object> params = new HashMap();
        params.put("table", table);
        params.put("packageConfig", packageConfig);
        params.put("javadocConfig", globalConfig.getJavadocConfig());

        // custom
        params.put("clientClassName", clientClassName);
        params.put("clientPackageName", clientPackageName);
        params.put("interfaceClassName", U.getInterfaceClassName(table));
        params.put("interfacePackageName", U.getInterfacePackageName());
        params.put("feignValue", AppConfig.FEIGN_PREFIX + "service-" + AppConfig.MODEL);
        params.put("modelName", AppConfig.MODEL);
        params.putAll(globalConfig.getCustomConfig());
        globalConfig.getTemplateConfig().getTemplate().generate(params, this.templatePath, dataJavaFile);
        System.out.println("Client ---> " + dataJavaFile);
    }

    public String getTemplatePath() {
        return this.templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

}
