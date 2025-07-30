package com.scheduler.core;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public abstract class AbstractJob implements Job{

	private final String jobId=UUID.randomUUID().toString();
	private final  int priority;
	private int retryCount;
	private final int maxRetries;
	private JobStatus status;
	
	
	
	public AbstractJob(int priority,int maxRetryCount) {
		this.priority=priority;
		this.maxRetries=maxRetryCount;
		
	}
	
	@Override
	public int getPriority() {
		return this.priority;
	}
	
	@Override
	public String getJobId() {
		return this.jobId;
	}
	
	@Override
	public int getRetryCount() {
		return this.retryCount;
	}
	

	@Override
	public void incrementRetryCount() {
		this.retryCount+=1;
	}
	
	@Override
	public int getMaxRetries() {

		return this.maxRetries;
	}
	
	@Override
	public long getInitialDelayMillis() {
	
		return this.maxRetries;
	}
	
	@Override
	public JobStatus getStatus() {
		
		return this.status;
	}
	
	@Override
	public void setStatus(JobStatus status) {
		this.status=status;
	}
	
	
}
