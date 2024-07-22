package com.roger.springscheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
public class Tasks {
    // 獲取台灣時間的實例
    TimeZone timeZone = TimeZone.getTimeZone("Asia/Taipei");

    /**
     * 使用 @Scheduled 註解來配置一個定時任務。
     * fixedDelay 參數設置為 30000 毫秒（即 30 秒），
     * 表示每次任務執行結束後，會等待 30 秒再開始下一次執行。
     * zone 參數設置為 "Asia/Taipei"，確保任務在台灣時區內運行。
     */
    @Scheduled(fixedDelay = 30000, zone = "timeZone")
    public  void TaskDequeueFixedDelay() {
        // 任務執行的邏輯在這裡實現。
        // 這個例子只是打印一條消息，實際應用中你可以在這裡實現具體的業務邏輯，比如處理消息隊列中的消息。
        System.out.println("Executing TaskDequeueFixedDelay every 30 seconds with fixed delay in Taiwan time zone.");
    }

    /**
     * 每五秒顯示一次
     */
    @Scheduled(cron = "*/5 * * * * ?")
    public void everyFiveSecond() {
        System.out.println("Execution Frequent Job every 5 seconds By Spring Scheduler");
    }
}
