package com.timeoutzero.flice.core.security;

import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.oauth.OAuthProvider;

import javax.ws.rs.ext.Provider;

import com.google.inject.Inject;
import com.timeoutzero.flice.core.model.User;

@Provider
public class AuthProvider extends OAuthProvider<User> {

	private static final String OAUTH2_PROVIDER = "oauth2-provider";

	public AuthProvider(Authenticator<String, User> authenticator, String realm) {
		super(authenticator, realm);
	}
	
	@Inject
	public AuthProvider(CoreAuthenticator authenticator) {
		super(authenticator, OAUTH2_PROVIDER);
	}
}