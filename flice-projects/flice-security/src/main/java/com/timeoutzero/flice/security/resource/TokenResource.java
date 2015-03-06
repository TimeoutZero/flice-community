package com.timeoutzero.flice.security.resource;

import io.dropwizard.hibernate.UnitOfWork;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
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

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.sun.jersey.api.Responses;
import com.timeoutzero.flice.security.dao.AccessTokenDAO;
import com.timeoutzero.flice.security.model.AccessToken;

@Path("/token")
@Produces(MediaType.APPLICATION_JSON)
public class TokenResource {
	
	private static final String ACCESS_TOKEN_KEY = "accessToken";
	
	@Inject
	private HttpClient httpClient;
	
	@Inject
	private AccessTokenDAO tokenDAO;
	
	@Inject
	private ImmutableList<String> allowedGrantType;
 
	@POST
	@UnitOfWork
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Map<String, String> postForToken(
			@FormParam("grant_type") String grantType,
			@FormParam("client_id") String clientId, 
			@FormParam("email") String email,
			@FormParam("password") String password) {
		
		isValidGrantType(grantType);
		isValidClientId(clientId);
		
		Map<String, String> json = new HashMap<>(); 

		if(isValidUser(email, password)) {
			
			AccessToken token = tokenDAO.generateAccessToken(email); 
			json.put(ACCESS_TOKEN_KEY, token.getToken());
		}
		
		return json;
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
