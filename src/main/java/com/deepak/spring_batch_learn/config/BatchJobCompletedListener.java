package com.deepak.spring_batch_learn.config;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class BatchJobCompletedListener implements JobExecutionListener {

    @Override
    public void afterJob(JobExecution jobExecution){
        if(jobExecution.getStatus() == BatchStatus.COMPLETED){
            System.out.println("Job completed");
        }
    }
}
