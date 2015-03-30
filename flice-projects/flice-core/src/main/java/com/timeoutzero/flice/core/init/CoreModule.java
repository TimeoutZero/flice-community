package com.timeoutzero.flice.core.init;

import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Environment;

import org.apache.http.client.HttpClient;
import org.hibernate.SessionFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class CoreModule extends AbstractModule {

	private static final String CLIENT = "client";
	
	private HibernateBundle<CoreConfiguration> hibernate;

	public CoreModule(HibernateBundle<CoreConfiguration> hibernate) {
		this.hibernate = hibernate;
	}
	
	@Override
	protected void configure() {
	
		bind(SessionFactory.class).toProvider(new Provider<SessionFactory>() {

			@Override
			public SessionFactory get() {
				return hibernate.getSessionFactory();
			}
		});
	}
	
	@Provides @Singleton
	public HttpClient httpClient(Environment environment, CoreConfiguration configuraiton) {
		return new HttpClientBuilder(environment).using(configuraiton.getHttpClient()).build(CLIENT);
	}
	
	@Provides
	@Singleton
	public String securityApiUrl(CoreConfiguration configuration) {
		return configuration.getSecurityApiUrl();
	}
}
