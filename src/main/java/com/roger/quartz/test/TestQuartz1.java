package com.roger.quartz.test;

import com.roger.quartz.MyJob1;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.GregorianCalendar;

public class TestQuartz1 {
    public static void main(String[] args) throws Exception {
        // 設置 SehedulerFactory 並載入 quartz.properties 配置文件
        SchedulerFactory schedulerFactory = new StdSchedulerFactory("quartz.properties");
        // 創建 Scheduler，調度器
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 啟動調度器
        scheduler.start();
        // 定義一個 Trigger，觸發條件類
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1") // 定義名稱 / 群組
                .startNow() // 設置開始時間，一旦加入 scheduler，表示立即生效，即開始計時
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2) // 每隔 2 秒執行一次
                        // .repeatForever()) // 一直執行，直到結束時間
                        .withRepeatCount(3)) // 設置執行次數
                // 設置結束時間（注：月份默認從 0 開始）
                .endAt(new GregorianCalendar(2024, 7, 25, 17, 30, 10).getTime())
                .build();

        // 定義一個 JobDetail
        JobDetail job = JobBuilder.newJob(MyJob1.class)
                .withIdentity("job1","group1") // 定義 name / group
                .build();
        // 調度器中加入任務和觸發器
        scheduler.scheduleJob(job, trigger);
        // 啟動任務調度
        scheduler.start();
    }
}


