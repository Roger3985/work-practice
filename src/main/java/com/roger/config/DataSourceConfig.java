package com.roger.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Order(Integer.MIN_VALUE)
@Configuration
public class DataSourceConfig {

    @Value("${mybatis.datasource.url}")
    private String url;

    @Value("${mybatis.datasource.username}")
    private String username;

    @Value("${mybatis.datasource.password}")
    private String password;

    @Value("${mybatis.datasource.driver-class-name}")
    private String driverClassName;
}
