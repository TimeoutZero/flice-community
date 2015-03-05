package com.timeoutzero.flice.core.dto;

import lombok.Getter;

import com.timeoutzero.flice.core.model.User;


public class UserDTO {

	@Getter
	private String email;
	
	public UserDTO(User user) {
		super();
		this.email   = user.getEmail();
	}
}
