package com.timeoutzero.flice.notification.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private MailSender mailSender;
	
	public void sendEmailNotification(List<String> receivers, String message){
		try{
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setFrom("flice@gmail.com");
			
			String[] receiversArray = new String[receivers.size()];
			mail.setTo(receivers.toArray(receiversArray));
			mail.setSubject("Notificação");
			mail.setText(message);
			mailSender.send(mail);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
