package com.example.util;

import com.example.config.AppConfig;
import com.mybatisflex.codegen.config.PackageConfig;
import com.mybatisflex.codegen.entity.Table;

import java.util.Arrays;
import java.util.stream.Collectors;

public class U {
    private static final String DATA_PACKAGE = "data";
    private static final String SERVICE_PACKAGE = "service";
    private static final String DTO_PACKAGE = "dto";
    private static final String VO_PACKAGE = "vo";
    private static final String WEB_PACKAGE = "web";
    private static final String DATA_IMPL_PACKAGE = "data.impl";
    private static final String SERVICE_IMPL_PACKAGE = "service.impl";
    private static final String CONTROLLER = "controller";

    public static String getDataClassName(Table table) {
        return getClassName(table, DATA_PACKAGE);
    }

    public static String getDataPackageName() {
        return getPackageName(DATA_PACKAGE);
    }

    public static String getDataImplClassName(Table table) {
        return getClassName(table, DATA_IMPL_PACKAGE);
    }

    public static String getDataImplPackageName() {
        return getPackageName(DATA_IMPL_PACKAGE);
    }

    public static String getDtoClassName(Table table) {
        return getClassName(table, DTO_PACKAGE.toUpperCase());
    }

    public static String getDtoPackageName() {
        return AppConfig.BASE_PACKAGE + "." + WEB_PACKAGE + "." + DTO_PACKAGE;
    }

    public static String getVoClassName(Table table) {
        return getClassName(table, VO_PACKAGE.toUpperCase());
    }

    public static String getVoPackageName() {
        return AppConfig.BASE_PACKAGE + "." + WEB_PACKAGE + "." + VO_PACKAGE;
    }

    public static String getInterfaceClassName(Table table) {
        return getClassName(table, "Interface");
    }

    public static String getInterfacePackageName() {
        return AppConfig.BASE_PACKAGE + ".provider.client." + AppConfig.MODEL + ".constract";
    }

    public static String getClientClassName(Table table) {
        return getClassName(table, "Client");
    }

    public static String getClientPackageName() {
        return AppConfig.BASE_PACKAGE + ".provider.client." + AppConfig.MODEL;
    }

    public static String getRequestClassName(Table table) {
        return getClassName(table, "Request");
    }

    public static String getRequestPackageName() {
        return AppConfig.BASE_PACKAGE + ".provider.client." + AppConfig.MODEL + ".request";
    }

    public static String getResponseClassName(Table table) {
        return getClassName(table, "Response");
    }

    public static String getResponsePackageName() {
        return AppConfig.BASE_PACKAGE + ".provider.client." + AppConfig.MODEL + ".response";
    }

    public static String getWebDataPackageName() {
        return AppConfig.BASE_PACKAGE + "." + DATA_PACKAGE;
    }

    public static String getWebDataImplPackageName() {
        return AppConfig.BASE_PACKAGE + "." + DATA_IMPL_PACKAGE;
    }

    public static String getWebServicePackageName() {
        return AppConfig.BASE_PACKAGE + "." + SERVICE_PACKAGE;
    }

    public static String getWebServiceImplPackageName() {
        return AppConfig.BASE_PACKAGE + "." + SERVICE_IMPL_PACKAGE;
    }

    public static String getWebControllerPackageName() {
        return AppConfig.BASE_PACKAGE + "." + WEB_PACKAGE + "." + CONTROLLER;
    }

    private static String getClassName(Table table, String packageName) {
        return table.buildEntityClassName() + capitalize(packageName);
    }

    private static String getPackageName(String packageName) {
        return AppConfig.BASE_PACKAGE + "." + AppConfig.MODEL + "." + packageName;
    }

    private static String capitalize(String packageName) {
        String[] split = packageName.split("\\.");
        return Arrays.stream(split)
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .collect(Collectors.joining(""));
    }
}
