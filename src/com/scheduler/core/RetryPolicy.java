package com.scheduler.core;

public class RetryPolicy {

	public static  Long getRetryDelay(int retryCount) {
		
		return  (long)Math.pow(retryCount, 2)*1000L;
	}
}
