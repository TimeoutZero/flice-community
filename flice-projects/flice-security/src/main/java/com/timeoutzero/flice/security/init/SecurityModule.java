package com.timeoutzero.flice.security.init;

import io.dropwizard.hibernate.HibernateBundle;

import org.hibernate.SessionFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;

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
}
