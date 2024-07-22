package com.roger.springscheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
public class Tasks {
    // 獲取台灣時間的實例
    TimeZone timeZone = TimeZone.getTimeZone("Asia/Taipei");

    /**
     * 每五秒顯示一次
     */
    @Scheduled(cron = "*/5 * * * * ?")
    public void everyFiveSecond() {
        System.out.println("Execution Frequent Job every 5 seconds By Spring Scheduler");
    }
}
