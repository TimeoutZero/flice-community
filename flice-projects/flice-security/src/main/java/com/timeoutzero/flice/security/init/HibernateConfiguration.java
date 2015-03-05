package com.timeoutzero.flice.security.init;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import lombok.Getter;

import com.timeoutzero.flice.security.model.AccessToken;

public class HibernateConfiguration {
	
	@Getter
	private final HibernateBundle<SecurityConfiguration> bundle = new HibernateBundle<SecurityConfiguration>(AccessToken.class) {

		@Override
		public DataSourceFactory getDataSourceFactory(SecurityConfiguration configuration) {
			return null;
		}
	};
}
