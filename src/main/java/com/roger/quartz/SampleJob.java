package com.roger.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SampleJob implements Job {


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Executing Frequent Job every 5 minutes!");
    }
}
