package com.timeoutzero.flice.core.init;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.hubspot.dropwizard.guice.GuiceBundle;

public class CoreApplication extends Application<CoreConfiguration>{

	private static final String CORE_URL_API = "/core/api/*";
	private static final String PACKAGE_RESOURCE = "com.timeoutzero.flice.core.resource";
	

	public static void main(String[] args) throws Exception {
		new CoreApplication().run(args);
	}
	
	@Override
	public void initialize(Bootstrap<CoreConfiguration> bootstrap) {
		
		GuiceBundle<CoreConfiguration> guice = GuiceBundle.<CoreConfiguration>newBuilder()
				.enableAutoConfig(PACKAGE_RESOURCE)
				.addModule(module -> {})
				.setConfigClass(CoreConfiguration.class)
				.build();
	
		bootstrap.addBundle(guice);
	}

	@Override
	public void run(CoreConfiguration configuration, Environment environment) throws Exception {
		environment.jersey().setUrlPattern(CORE_URL_API);
	}
}
