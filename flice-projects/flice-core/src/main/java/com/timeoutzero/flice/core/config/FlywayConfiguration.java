package com.timeoutzero.flice.core.config;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayBundle;
import io.dropwizard.flyway.FlywayFactory;
import lombok.Getter;

import com.timeoutzero.flice.core.init.CoreConfiguration;

public class FlywayConfiguration {

	@Getter
	private FlywayBundle<CoreConfiguration> bundle = new FlywayBundle<CoreConfiguration>() {

		@Override
		public DataSourceFactory getDataSourceFactory(CoreConfiguration configuration) {
			return configuration.getDatasource();
		}
		
		@Override
		public FlywayFactory getFlywayFactory(CoreConfiguration configuration) {
			return configuration.getFlyway();
		}
	};
}
