package com.roger.config;

import com.roger.quartz.FrequentJob;
import com.roger.quartz.MyJob2;
import com.roger.quartz.SampleJob;
import org.quartz.*;
import org.quartz.spi.JobFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail myJob2Detail() {
        return JobBuilder.newJob(MyJob2.class)
                .withIdentity("myJob2")
                .storeDurably()
                .build();
    }

    @Bean
    public JobDetail sampleJobDetail() {
        return JobBuilder.newJob(SampleJob.class)
                .withIdentity("sampleJob")
                .storeDurably()
                .build();
    }

    @Bean
    public JobDetail frequentJobDetail() {
        return JobBuilder.newJob(FrequentJob.class)
                .withIdentity("frequentJob")
                .storeDurably()
                .build();
    }

    /**
     * 每 5秒 顯示一次
     */
    @Bean
    public Trigger myJob2Trigger(JobDetail myJob2Detail) {
        return TriggerBuilder.newTrigger()
                .forJob(myJob2Detail)
                .withIdentity("myJob2Trigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))
                .build();
    }

    /**
     * 每 5分鐘 顯示一次
     */
    @Bean
    public Trigger frequentJobTrigger(JobDetail frequentJobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(frequentJobDetail)
                .withIdentity("frequentTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/5 * * * ?"))
                .build();
    }

    /**
     * 每日 10 點會呼叫一次
     */
    @Bean
    public Trigger sampleJobTrigger(JobDetail sampleJobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(sampleJobDetail)
                .withIdentity("sampleTrigger")
                // 每日 10 點會呼叫一次
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(10, 0))
                .build();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(Trigger myJob2Trigger, Trigger sampleJobTrigger, Trigger frequentJobTrigger, JobDetail myJob2Detail, JobDetail sampleJobDetail, JobDetail frequentJobDetail, JobFactory jobFactory) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(jobFactory);
        schedulerFactoryBean.setJobDetails(myJob2Detail, sampleJobDetail, frequentJobDetail);
        schedulerFactoryBean.setTriggers(myJob2Trigger, sampleJobTrigger, frequentJobTrigger);
        return schedulerFactoryBean;
    }
}
