package com.timeoutzero.flice.notification.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timeoutzero.flice.notification.service.NotificationService;

@RestController
@RequestMapping(value="/notification")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;
	
	@RequestMapping(method=RequestMethod.POST)
	public Boolean sendNotification(@RequestParam("message") String message) throws IOException, InterruptedException{
		
		notificationService.sendMessage(message);
		
		return true;
	}
	
}
