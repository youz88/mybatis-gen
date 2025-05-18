package com.example.config;

import lombok.Data;

import java.util.Properties;

@Data
public class AppConfig {

    public static String JDBC_URL;
    public static String JDBC_USERNAME;
    public static String JDBC_PASSWORD;

    public static String BASE_PACKAGE;
    public static String MODEL;

    public static String TABLE_PREFIX;
    public static String GENERATE_TABLE;

    public static String FEIGN_PREFIX;

    public static void fromProperties(Properties props) {
        BASE_PACKAGE = props.getProperty("basePackage");
        MODEL = props.getProperty("model");
        JDBC_URL = props.getProperty("jdbcUrl");
        JDBC_USERNAME = props.getProperty("username");
        JDBC_PASSWORD = props.getProperty("password");
        TABLE_PREFIX = props.getProperty("tablePrefix");
        GENERATE_TABLE = props.getProperty("generateTable");
        FEIGN_PREFIX = props.getProperty("feignPrefix");
    }

}
