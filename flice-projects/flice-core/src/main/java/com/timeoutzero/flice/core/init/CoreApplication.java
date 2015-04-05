package com.timeoutzero.flice.core.init;

import io.dropwizard.Application;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.timeoutzero.flice.core.config.HibernateConfiguration;

public class CoreApplication extends Application<CoreConfiguration>{

	private static final String CORE_URL_API 	 = "/core/api/*";
	private static final String PACKAGE_DAO		 = "com.timeoutzero.flice.core.dao";
	private static final String PACKAGE_RESOURCE = "com.timeoutzero.flice.core.resource";
	private static final String PACKAGE_SECURITY = "com.timeoutzero.flice.core.security";

	private final HibernateBundle<CoreConfiguration> hibernate = new HibernateConfiguration().getBundle();
	
	public static void main(String[] args) throws Exception {
		new CoreApplication().run(args);
	}
	
	@Override
	public void initialize(Bootstrap<CoreConfiguration> bootstrap) {

		GuiceBundle<CoreConfiguration> guice = GuiceBundle.<CoreConfiguration>newBuilder()
				.enableAutoConfig(PACKAGE_DAO, PACKAGE_RESOURCE, PACKAGE_SECURITY)
				.addModule(new CoreModule(hibernate))
				.setConfigClass(CoreConfiguration.class)
				.build(Stage.DEVELOPMENT);
		
		bootstrap.addBundle(guice);
		bootstrap.addBundle(hibernate);
	}

	@Override
	public void run(CoreConfiguration configuration, Environment environment) throws Exception {
		
		//Environment 
		environment.jersey().setUrlPattern(CORE_URL_API);
		
		//Custom Tasks
		StartupModule.executeFlywayMigration(configuration);
	}
}
