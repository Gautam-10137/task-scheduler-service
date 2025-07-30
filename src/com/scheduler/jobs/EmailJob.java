package com.scheduler.jobs;

import java.util.concurrent.CountDownLatch;

import com.scheduler.core.AbstractJob;
import com.scheduler.core.JobStatus;

public class EmailJob extends AbstractJob{
	
	private final String recipient;
	
	public EmailJob(String recipient,int priority, int maxRetryCount) {
		super(priority, maxRetryCount);
		this.recipient=recipient;
	}

	@Override
	public void run() {
		
		this.setStatus(com.scheduler.core.JobStatus.RUNNING);
        System.out.println("[EmailJob] Sending email to: " + recipient);
        if (Math.random() < 0.4) {
            throw new RuntimeException("Simulated email failure");
        }
        System.out.println("[EmailJob] Email sent successfully to: " + recipient);
        this.setStatus(com.scheduler.core.JobStatus.SUCCESS);
		
	}

	
}
