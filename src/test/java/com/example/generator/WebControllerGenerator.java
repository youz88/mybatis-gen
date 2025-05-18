package com.example.generator;

import com.example.util.U;
import com.mybatisflex.codegen.config.ControllerConfig;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.config.PackageConfig;
import com.mybatisflex.codegen.constant.TemplateConst;
import com.mybatisflex.codegen.entity.Table;
import com.mybatisflex.codegen.generator.IGenerator;
import com.mybatisflex.core.util.StringUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class WebControllerGenerator implements IGenerator {

    private String templatePath;

    public WebControllerGenerator() {
        this(TemplateConst.CONTROLLER);
    }

    public WebControllerGenerator(String templatePath) {
        this.templatePath = templatePath;
    }

    @Override
    public void generate(Table table, GlobalConfig globalConfig) {

        if (!globalConfig.isControllerGenerateEnable()) {
            return;
        }

        PackageConfig packageConfig = globalConfig.getPackageConfig();

        String webControllerClassName = table.buildControllerClassName();
        String webControllerPackageName = U.getWebControllerPackageName();

        ControllerConfig controllerConfig = globalConfig.getControllerConfig();

        String sourceDir = StringUtil.hasText(controllerConfig.getSourceDir()) ? controllerConfig.getSourceDir() : packageConfig.getSourceDir();

        String controllerPackagePath = webControllerPackageName.replace(".", "/");
        File controllerJavaFile = new File(sourceDir, controllerPackagePath + "/" +
                webControllerClassName + globalConfig.getFileType());


        if (controllerJavaFile.exists() && !controllerConfig.isOverwriteEnable()) {
            return;
        }


        Map<String, Object> params = new HashMap<>(4);
        params.put("table", table);
        params.put("packageConfig", packageConfig);
        params.put("controllerConfig", controllerConfig);
        params.put("javadocConfig", globalConfig.getJavadocConfig());
        params.put("withSwagger", globalConfig.isEntityWithSwagger());
        params.put("swaggerVersion", globalConfig.getSwaggerVersion());

        // custom
        params.put("webControllerPackageName", webControllerPackageName);
        params.put("webServicePackageName", U.getWebServicePackageName());
        params.putAll(globalConfig.getCustomConfig());
        globalConfig.getTemplateConfig().getTemplate().generate(params, templatePath, controllerJavaFile);

        System.out.println("WebController ---> " + controllerJavaFile);
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
