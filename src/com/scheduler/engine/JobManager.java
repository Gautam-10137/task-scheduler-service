package com.scheduler.engine;

import com.scheduler.core.Job;

public class JobManager {

	private JobQueue jobQ;
	
	public JobManager(JobQueue jQ) {
		this.jobQ=jQ;
	}
	
	public void submit(Job job) {
		System.out.println("[JobManager] Submitting job with JobId: " + job.getJobId());
		jobQ.add(job);
	}
	
}
