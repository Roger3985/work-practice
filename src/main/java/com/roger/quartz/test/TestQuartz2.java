package com.roger.quartz.test;

import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestQuartz2 {
    public static void main(String[] args) throws InterruptedException, SchedulerException {
        // 工廠啟動，任務啟動，工廠關閉，任務停止
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
}
