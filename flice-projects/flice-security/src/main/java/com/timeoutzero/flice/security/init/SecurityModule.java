package com.timeoutzero.flice.security.init;

import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Environment;

import org.apache.http.client.HttpClient;
import org.hibernate.SessionFactory;

import com.google.common.collect.ImmutableList;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class SecurityModule extends AbstractModule {
	
	private HibernateBundle<SecurityConfiguration> hibernate;

	public SecurityModule(HibernateBundle<SecurityConfiguration> hibernate) {
		this.hibernate = hibernate;
	}

	@Override
	protected void configure() {
		bind(SessionFactory.class).toProvider(new Provider<SessionFactory>() {
 
			@Override
			public SessionFactory get() {
				return hibernate.getSessionFactory();
			}
		});;
	}
	
	@Provides
	public ImmutableList<String> allowedGrantType(SecurityConfiguration configuration) {
		return configuration.getAllowedGrantTypes();
	}
	
	@Provides @Singleton
	public HttpClient httpClient(Environment environment, SecurityConfiguration configuraiton) {
		return new HttpClientBuilder(environment).using(configuraiton.getHttpClient()).build("httpClient");
	}
	
}
