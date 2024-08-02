package com.roger;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author : Roger
 */
@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = "com.*")
// 指 mapper 介   面所在包,及 mapper 介面所用注解。
@MapperScan(basePackages = "com.roger.*.mapper", annotationClass = Mapper.class)
public class WorkPracticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorkPracticeApplication.class, args);
    }
}
