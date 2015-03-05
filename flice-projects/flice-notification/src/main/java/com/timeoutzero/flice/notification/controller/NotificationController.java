package com.timeoutzero.flice.notification.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.timeoutzero.flice.notification.dto.EmailNotificationDTO;
import com.timeoutzero.flice.notification.service.NotificationService;

@RestController
@RequestMapping(value="/notification")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;
	
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Boolean sendEmailNotification(@RequestBody EmailNotificationDTO emailNotification) throws IOException, InterruptedException{
		
		notificationService.sendMessage(emailNotification);
		
		return true;
	}
	
}
