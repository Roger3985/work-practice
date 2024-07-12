package com.roger.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import javax.sql.DataSource;

@Configuration
public class MyBatisConfig {

    @Bean
    public  SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        // 設置 MyBatis 的日誌輸出方式
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setLogImpl(StdOutImpl.class); // 使用標準輸出方式 STDOUT_LOGGING
        sessionFactoryBean.setConfiguration(configuration);
        // Resource mybatisConfig = new ClassPathResource("mybatis-config.xml");
        // sessionFactoryBean.setConfigLocation(mybatisConfig);
        // 全部的 mappers 設置
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml"));
        return sessionFactoryBean;
    }
}
