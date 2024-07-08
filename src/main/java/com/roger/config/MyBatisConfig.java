package com.roger.config;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import javax.sql.DataSource;

@Configuration
public class MyBatisConfig {

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        // 設置 MyBatis 的日誌輸出方式
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setLogImpl(StdOutImpl.class); // 使用標準輸出方式 STDOUT_LOGGING
        sessionFactoryBean.setConfiguration(configuration);
        // 全部的 mappers 設置
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml"));
        return sessionFactoryBean;
    }

    /**
     * mapper config
     * 此跟 spring @Mapperscan 擇一
     * mapper 代理物件加入 ioc
     * @return MapperScannerConfigurer config 物件
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        // Mapper 代理物件的 FactoryBean -> 指定一個包 -> mapper 介面 -> sqlSessionFactory -> sqlSession -> getMapper -> mapper 代理物件 -> ioc
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        // mapper interface and mapper xml same package
        mapperScannerConfigurer.setBasePackage("com.roger.*.mapper");
        mapperScannerConfigurer.setAnnotationClass(Mapper.class);
        return mapperScannerConfigurer;
    }
}
