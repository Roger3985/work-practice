//package com.roger.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.TransactionDefinition;
//import org.springframework.transaction.TransactionStatus;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.transaction.support.DefaultTransactionDefinition;
//import org.springframework.transaction.support.TransactionTemplate;
//
//import javax.sql.DataSource;
//
///**
// * TransactionTemplate 相關配置
// */
//@Configuration
//@EnableTransactionManagement
//public class TransactionConfig {
//
//    @Bean(name = "transactionManagerReadCommitted")
//    public PlatformTransactionManager transactionManagerReadCommitted(DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean
//    public TransactionTemplate transactionTemplate(DataSource dataSource) {
//        return new TransactionTemplate(transactionManagerReadCommitted(dataSource));
//    }
//
//    @Bean(name = "transactionManagerSerializable")
//    public PlatformTransactionManager transactionManagerSerializable(DataSource dataSource) {
//        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
//        dataSourceTransactionManager.setDefaultTimeout(30); // 設置交易超過時間
//        return dataSourceTransactionManager;
//    }
//}
