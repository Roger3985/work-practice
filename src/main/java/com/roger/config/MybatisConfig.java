package com.roger.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

@Configuration
public class MybatisConfig {

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        // Resource import spring core package
        Resource resource = new ClassPathResource("mybatis-config.xml");
        sessionFactoryBean.setConfigLocation(resource);
        return sessionFactoryBean;
    }

    // mapper 代理物件加入 ioc
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        // Mapper 代理物件的 FactoryBean -> 指定一個包 -> mapper 介面 -> sqlSessionFactory -> sqlSession -> getMapper -> mapper 代理物件 -> ioc
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        // mapper interface and mapper xml same package
        mapperScannerConfigurer.setBasePackage("com.roger.mapper");
        return mapperScannerConfigurer;
    }
}
