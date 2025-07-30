package com.scheduler.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class JobExecutorStarter {


	private final JobExecutor jobExecutor;

    public JobExecutorStarter(JobQueue jobQueue) {
        this.jobExecutor = new JobExecutor(jobQueue, 4); 
    }

    @PostConstruct
    public void startExecutor() {
        System.out.println("[JobExecutorStarter] Starting job executor...");
        jobExecutor.start();
    }
    
}
