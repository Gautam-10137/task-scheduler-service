package com.scheduler.engine;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

import com.scheduler.core.Job;
@Component
public class JobQueue {

	private PriorityBlockingQueue<Job> queue;
	
	public JobQueue(){
		Comparator<Job> comparator= Comparator.comparingInt(Job::getPriority).reversed();
		this.queue=new PriorityBlockingQueue<Job>(100,comparator);
	}
	
	public void add(Job job) {
		queue.offer(job);
	}
	
	public Job take() throws InterruptedException {
		return queue.take();
	}
	
}
