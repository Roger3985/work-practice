package com.roger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author : Roger
 */
@SpringBootApplication
// 指 mapper 介面所在包,及 mapper 介面所用注解。
//@MapperScan(basePackages = "com.roger.*.mapper", annotationClass = Mapper.class)
public class WorkPracticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorkPracticeApplication.class, args);
    }
}
