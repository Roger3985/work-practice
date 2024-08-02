package com.roger.quartz;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * 工具類的具體實現，即需要定時執行的某件事
 */
public class MyJob1 implements Job {

    // 執行
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // 創建工作詳情
        JobDetail jobDetail = context.getJobDetail();
        // 獲取工作名稱
        String jobName = jobDetail.getKey().getName(); // 任務名
        String jobGroup = jobDetail.getKey().getGroup(); // 任務 group
        System.out.println("job 執行, job: " + jobName + "group:" + jobGroup);
        System.out.println(new Date());
    }

}
