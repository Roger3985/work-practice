package com.roger.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configurator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Log4j2Config {

    /**
     * 用 yml 檔案配置還需要在 @SpringBootApplication 這邊配置或要在 config 中配置 @Bean
     */
    @Bean
    public Logger logger() {
        return LogManager.getLogger(getClass().getName());
    }

    @Bean
    public LoggerContext loggerContext() {
        LoggerContext context = Configurator.initialize("myApp", "classpath:log4j2-spring.xml");
        return context;
    }
}
