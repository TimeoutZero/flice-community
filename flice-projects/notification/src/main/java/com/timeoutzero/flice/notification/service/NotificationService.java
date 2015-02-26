package com.timeoutzero.flice.notification.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	@Autowired
	private Receiver receiver;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	private final static String QUEUE_NAME = "spring-boot";

	public void sendMessage(String message) throws IOException, InterruptedException{
		System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(QUEUE_NAME, message);
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
	}

}
