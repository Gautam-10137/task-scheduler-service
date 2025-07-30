package com.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jobs")
public class JobProducerController {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@PostMapping
	public ResponseEntity<?> publishJob(@RequestBody String jobPayload){
		
		kafkaTemplate.send("job-events",jobPayload);
		return new ResponseEntity<>("Job published to kafka topic!",HttpStatus.OK);
	}
}
