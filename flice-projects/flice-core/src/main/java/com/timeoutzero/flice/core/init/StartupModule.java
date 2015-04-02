package com.timeoutzero.flice.core.init;

import io.dropwizard.db.DataSourceFactory;

import org.flywaydb.core.Flyway;

public class StartupModule {

	public static void executeFlywayMigration(CoreConfiguration configuration) {
		
		DataSourceFactory db = configuration.getDatasource(); 
		
		Flyway flyway = new Flyway();
		flyway.setDataSource(db.getUrl(), db.getUser(), db.getPassword());
		flyway.migrate();
	}
}
