package com.timeoutzero.flice.notification.dto;

import java.util.List;

public class EmailNotificationDTO{

	private List<String> receivers;
	
	private String message;

	/**
	 * @return the receivers
	 */
	public List<String> getReceivers() {
		return receivers;
	}

	/**
	 * @param receivers the receivers to set
	 */
	public void setReceivers(List<String> receivers) {
		this.receivers = receivers;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
