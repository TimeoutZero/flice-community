package com.timeoutzero.flice.notification.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Receiver implements MessageListener{

	private CountDownLatch latch = new CountDownLatch(1);

	@Autowired
	private EmailService emailService;
	
	@SuppressWarnings("unchecked")
	public void onMessage(Message message) {
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(message.getBody());
			ObjectInputStream is;
			is = new ObjectInputStream(in);
			Map<String, Object> map = (Map<String, Object>) is.readObject();
			
			List<String> receivers = (List<String>) map.get("receivers");
			String messageText = (String) map.get("message");
			
			System.out.println("Received <"+map.get("message")+">");
			
			emailService.sendEmailNotification(receivers, messageText);
			
			latch.countDown();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public CountDownLatch getLatch() {
		return latch;
	}

}
