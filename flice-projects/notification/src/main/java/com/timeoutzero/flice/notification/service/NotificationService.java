package com.timeoutzero.flice.notification.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timeoutzero.flice.notification.dto.EmailNotificationDTO;

@Service
public class NotificationService {

	@Autowired
	private Receiver receiver;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	private final static String QUEUE_NAME = "spring-boot";

	public void sendMessage(EmailNotificationDTO emailNotification) throws IOException, InterruptedException{
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("receivers", emailNotification.getReceivers());
		map.put("message", emailNotification.getMessage());
		
		System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(QUEUE_NAME, map);
        receiver.getLatch().await(1000, TimeUnit.MILLISECONDS);
	}

}
	