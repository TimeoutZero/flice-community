package com.timeoutzero.flice.core.config;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import lombok.Getter;

import com.timeoutzero.flice.core.init.CoreConfiguration;
import com.timeoutzero.flice.core.model.User;



@Getter
public class HibernateConfiguration {

	private HibernateBundle<CoreConfiguration> bundle = new HibernateBundle<CoreConfiguration>(User.class) {

		@Override
		public DataSourceFactory getDataSourceFactory(final CoreConfiguration configuration) {
			return configuration.getDatasource();
		}
	};
}
