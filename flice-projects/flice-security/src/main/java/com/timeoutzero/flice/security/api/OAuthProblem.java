package com.timeoutzero.flice.security.api;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OAuthProblem {

	public static final String SESSION_HAS_EXPIRED = "Error validating access token: Session has expired on %s. The current time is %s";

	@NotNull
	private String message; 
	
}
