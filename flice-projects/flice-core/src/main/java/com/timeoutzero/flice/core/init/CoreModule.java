package com.timeoutzero.flice.core.init;

import io.dropwizard.hibernate.HibernateBundle;

import org.hibernate.SessionFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;

public class CoreModule extends AbstractModule {

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

}
