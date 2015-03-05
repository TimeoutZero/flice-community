package com.timeoutzero.flice.core.form;

import lombok.Setter;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.mindrot.jbcrypt.BCrypt;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.timeoutzero.flice.core.model.User;

@Setter
public class UserForm {

	@Email
	@NotBlank
	@JsonProperty
	private String email;
	
	@NotBlank
	@JsonProperty
	private String password;

	public User toEntity(){
		
		String gensalt  = BCrypt.gensalt(5);
		String password = BCrypt.hashpw(this.password, gensalt);
		
		User user = new User();
		user.setEmail(this.email);
		user.setPassword(password);
		
		return user;
	}
}
