package com.timeoutzero.flice.security.api;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
public class User {

	@JsonProperty
	private String email;
	
	@JsonProperty
	private String password;
}
