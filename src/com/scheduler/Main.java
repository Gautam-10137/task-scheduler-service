package com.scheduler;

import java.util.concurrent.CountDownLatch;

import com.scheduler.engine.*;
import com.scheduler.jobs.EmailJob;
import com.scheduler.jobs.ReportGenerationJob;


public class Main {

	public static void main(String[] args) throws InterruptedException {
		JobQueue jobQueue = new JobQueue();
        JobManager jobManager = new JobManager(jobQueue);
        JobExecutor executor = new JobExecutor(jobQueue, 4);

        executor.start();
        
        int totalJobs=3;

        CountDownLatch latch=new CountDownLatch(totalJobs);
        
        jobManager.submit(new EmailJob("gautam@domain.com", 10, 3,latch));
        jobManager.submit(new EmailJob("user@domain.com", 5, 2,latch));
        jobManager.submit(new ReportGenerationJob("Sales Report Q3", 8, 2, latch));
        
        latch.await();

        System.out.println("All jobs finished or exhausted.");
	}

}
