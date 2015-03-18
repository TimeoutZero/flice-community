package com.timeoutzero.flice.core.api;

import lombok.Getter;

import com.timeoutzero.flice.core.model.User;


public class UserDTO {

	@Getter
	private String email;
	
	public UserDTO(User user) {

		this.email   = user.getEmail();
	}
}
