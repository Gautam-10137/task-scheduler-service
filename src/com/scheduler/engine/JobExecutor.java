package com.scheduler.engine;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.scheduler.core.Job;
import com.scheduler.core.JobStatus;
import com.scheduler.core.RetryPolicy;

public class JobExecutor {

	private JobQueue jobQ;
	private ScheduledExecutorService scheduler;
	
	public JobExecutor(JobQueue jQ,int poolSize) {
		this.jobQ=jQ;
		this.scheduler=Executors.newScheduledThreadPool(poolSize);
	}
	
	public void start() {
		Thread worker = new Thread(() -> {

			while (true) {
				try {
					Job job = jobQ.take();
					
                    this.scheduleTask(job,job.getInitialDelayMillis());
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					break;
					
				}
			}

		});

		worker.setDaemon(true);
		worker.start();

	}
	
	public void scheduleTask(Job job,Long delayInMillis) {
		
		   scheduler.schedule(()->{
				try {
					
					System.out.println("[JobExecutor] Executing job: " + job.getJobId());
	                job.run();
	                if (job.getStatus() != JobStatus.SUCCESS) {
	                    throw new Exception("Job did not complete successfully");
	                }
	                
					
				}catch(Exception e) {
					System.err.println("[JobExecutor] Job failed: " + job.getJobId() + " | " + e.getMessage());
	                this.handleRetry(job);
				}
				 
				
			}, job.getInitialDelayMillis(), TimeUnit.MILLISECONDS);
		
		
	}
	
	public void handleRetry(Job job) {
		if(job.getRetryCount()<job.getMaxRetries()) {
			job.incrementRetryCount();
			job.setStatus(JobStatus.RETRYING);
			Long delay= RetryPolicy.getRetryDelay(job.getRetryCount());
			System.out.println("[JobExecutor] Retrying job: " + job.getJobId() + " after " + delay / 1000 + "s");
		    this.scheduleTask(job,delay);
		}
		else {
			job.setStatus(JobStatus.EXHAUSTED);
			System.out.println("[JobExecutor] Job exhausted: " + job.getJobId());
			
		}
		
	}
	
}
