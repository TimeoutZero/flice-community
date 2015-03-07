package com.timeoutzero.flice.core.security;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.timeoutzero.flice.core.dao.UserDAO;
import com.timeoutzero.flice.core.model.User;


public class CoreAuthenticator implements Authenticator<String, User> {

	private static final Logger log = LoggerFactory.getLogger(CoreAuthenticator.class);
	
	@Inject
	private HttpClient client; 

	@Inject
	private UserDAO userDAO;
	
	@Override
	public Optional<User> authenticate(String token) throws AuthenticationException {

		log.info("Attempt login with accessToken: {}", token);
		 
		Optional<String> email = getEmailByAccesToken(token);
		Optional<User> user = Optional.absent();
		
		if(email.isPresent()) {
			user = Optional.of(userDAO.findByEmail(email.get()));
		}
		
		return user;
	}

	private Optional<String> getEmailByAccesToken(String token) {
		
		String email = null;

		try {
			
			HttpUriRequest request = new HttpGet("http://localhost:8081/security/api/token/verify"); 
			request.addHeader("accessToken", token);
			
			HttpResponse response = client.execute(request);
			int statusCode = response.getStatusLine().getStatusCode();

			if(statusCode == HttpStatus.OK_200) {
				
				String json = EntityUtils.toString(response.getEntity());
				JsonNode result = new ObjectMapper().readTree(json);
				email = result.findValue("email").asText(); 
				
			} else {
				throw new WebApplicationException(Response.status(statusCode).entity(response.getEntity()).build());
			}
		
		} catch (IOException e) {
			throw new WebApplicationException(e);
		}
		
		return Optional.of(email);
	}

}
