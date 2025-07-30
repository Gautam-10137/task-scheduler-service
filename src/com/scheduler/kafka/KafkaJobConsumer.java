package com.scheduler.kafka;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scheduler.core.Job;
import com.scheduler.engine.JobQueue;
import com.scheduler.jobs.EmailJob;
import com.scheduler.jobs.ReportGenerationJob;

@Service
public class KafkaJobConsumer {

	@Autowired
	private JobQueue jobQueue;
	private final ObjectMapper objectMapper = new ObjectMapper();

	@KafkaListener(topics = "job-events", groupId = "job-schduler-consumer")
	public void consumerJobEvent(String message) {
		try {

			JsonNode node = objectMapper.readTree(message);
			String type = node.get("type").asText();
			int priority = node.get("priority").asInt();
			int maxRetries = node.get("maxRetries").asInt();
			

			Job job;

			switch (type.toLowerCase()) {
			case "email":
				job = new EmailJob(node.get("to").asText(), priority, maxRetries);
				break;
			case "report":
				job = new ReportGenerationJob(node.get("reportName").asText(), priority, maxRetries);
				break;
			default:
				throw new IllegalArgumentException("Unknown job type: " + type);

			}
			jobQueue.add(job);
	        System.out.println("[KafkaConsumer] Job submitted to queue: " + job.getJobId());

		} catch (Exception e) {
			System.err.println("[KafkaConsumer] Failed to parse/submit job: " + e.getMessage());
		}
	}

}
