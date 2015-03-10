package com.timeoutzero.flice.security.resource;

import io.dropwizard.hibernate.UnitOfWork;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpStatus;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.sun.jersey.api.Responses;
import com.timeoutzero.flice.security.api.OAuthProblem;
import com.timeoutzero.flice.security.dao.AccessTokenDAO;
import com.timeoutzero.flice.security.model.AccessToken;

@Path("/token")
@Produces(MediaType.APPLICATION_JSON)
public class TokenResource {
	
	private static final int ACCESS_TOKEN_EXPIRE_TIME_MIN = 30;
	
	@Inject
	private HttpClient httpClient;
	
	@Inject 
	private AccessTokenDAO accessTokenDAO;
	
	@Inject
	private ImmutableList<String> allowedGrantType;
	
	@GET
	@UnitOfWork
	@Path("/verify")
	public Response verify(@HeaderParam("accessToken") String accessToken){
		
		Optional<AccessToken> accessTokenOptional = accessTokenDAO.findByValue(accessToken);
		
		if(accessTokenOptional == null || !accessTokenOptional.isPresent()) {
			throw new WebApplicationException(HttpStatus.NOT_FOUND_404);
		}
		
		LocalDateTime lastAccess = accessTokenOptional.get().getLastAccess();
		
		long minutes = ChronoUnit.MINUTES.between(lastAccess, LocalDateTime.now());
		
		if(minutes > ACCESS_TOKEN_EXPIRE_TIME_MIN) {
			
			String lastAccessFormatted 	= lastAccess.toString();
			String currentTimeFormatted = LocalDateTime.now().toString();
			String message = String.format(OAuthProblem.SESSION_HAS_EXPIRED, lastAccessFormatted, currentTimeFormatted);
			
			throw new WebApplicationException(Response.status(HttpStatus.UNAUTHORIZED_401).entity(new OAuthProblem(message)).build());
		}
		
		accessTokenDAO.updateLastAccessTime(accessTokenOptional.get().getId());
		
		Map<String, String> entity = new HashMap<>();
		entity.put("email", accessTokenOptional.get().getEmail());
		
		return Response.status(HttpStatus.OK_200).entity(entity).build();
		
	}
 
	@POST
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response postForToken(
			@FormParam("grant_type") String grantType,
			@FormParam("client_id") String clientId, 
			@FormParam("email") String email,
			@FormParam("password") String password) {
		
		isValidGrantType(grantType);
		isValidClientId(clientId);
		
		AccessToken token = null;

		if(isValidUser(email, password)) {
			
			token = accessTokenDAO.generateAccessToken(email); 
		}
		
		return Response.status(HttpStatus.CREATED_201).entity(new AccessToken(token.getToken())).build();
	}

	private boolean isValidUser(String email, String password)  {
		
		boolean isValidUser = false;
		
		try {
			
			HttpPost request = new HttpPost("http://localhost:8080/core/api/user/verify");
			request.setHeader(HttpHeader.CONTENT_TYPE.toString(), MediaType.APPLICATION_FORM_URLENCODED);
			
			List<NameValuePair> params = new ArrayList<>();
			params.add(new BasicNameValuePair("email", email));
			params.add(new BasicNameValuePair("password", password));
			
			request.setEntity(new UrlEncodedFormEntity(params));
			
			int status = httpClient.execute(request).getStatusLine().getStatusCode();
			
			if(status == HttpStatus.OK_200) {
				isValidUser = true;
			} else {
				throw new WebApplicationException(status);
			}
			
			
		} catch (RuntimeException | IOException e) {
			throw new WebApplicationException(e);
		}
		
		return isValidUser;
	}
	

	private void isValidClientId(String clientId) {
	}

	private void isValidGrantType(String grantType) {
		
		if(!allowedGrantType.contains(grantType)) {
			
			Response response = Response.status(Responses.METHOD_NOT_ALLOWED).build();
			throw new WebApplicationException(response);
		}
	}
}
