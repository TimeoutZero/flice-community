package com.timeoutzero.flice.core.security;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;


public class CoreAuthenticator implements Authenticator<String, String> {

	private static final Logger log = LoggerFactory.getLogger(CoreAuthenticator.class);
	 
	@Override
	public Optional<String> authenticate(String token) throws AuthenticationException {

		log.info("Attempt login with accessToken: {}", token);

		
		return null;
	}

}
