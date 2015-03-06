package com.timeoutzero.flice.security.init;

import io.dropwizard.Application;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.hubspot.dropwizard.guice.GuiceBundle;

public class SecurityApplication extends Application<SecurityConfiguration>{

	private static final String SECURITY_URL_API 	= "/security/api/*";
	private static final String PACKAGE_RESOURCE 	= "com.timeoutzero.flice.security.resource";
	private static final String PACKAGE_DAO		 	= "com.timeoutzero.flice.security.dao";

	private HibernateBundle<SecurityConfiguration> hibernate = new HibernateConfiguration().getBundle();
	
	public static void main(String[] args) throws Exception {
		new SecurityApplication().run(args);
	}
	
	@Override
	public void initialize(Bootstrap<SecurityConfiguration> bootstrap) {
		
		GuiceBundle<SecurityConfiguration> guice = GuiceBundle.<SecurityConfiguration>newBuilder()
				.addModule(new SecurityModule(hibernate))
				.enableAutoConfig(PACKAGE_RESOURCE, PACKAGE_DAO)
				.setConfigClass(SecurityConfiguration.class)
				.build();
		
		bootstrap.addBundle(hibernate);
		bootstrap.addBundle(guice);
	}

	@Override
	public void run(SecurityConfiguration configuration, Environment environment) throws Exception {
		
		environment.jersey().setUrlPattern(SECURITY_URL_API);
		
	}
}
