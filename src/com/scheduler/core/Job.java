package com.scheduler.core;

import java.util.concurrent.CountDownLatch;

public interface Job extends Runnable{

	int getPriority();
	String getJobId();
	int getRetryCount();
	void incrementRetryCount();
	int getMaxRetries();
	long getInitialDelayMillis();
	CountDownLatch getLatch();
	
	JobStatus getStatus();
	void setStatus(JobStatus status);
	
	
}
