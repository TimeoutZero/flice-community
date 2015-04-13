package com.timeoutzero.flice.core.config;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;

import com.timeoutzero.flice.core.init.CoreConfiguration;
import com.timeoutzero.flice.core.model.Comment;
import com.timeoutzero.flice.core.model.Community;
import com.timeoutzero.flice.core.model.Topic;
import com.timeoutzero.flice.core.model.User;
import com.timeoutzero.flice.core.model.UserCommunity;
import com.timeoutzero.flice.core.model.UserTopic;

public class HibernateConfiguration {

	public static HibernateBundle<CoreConfiguration> withDefaultSettings(){
		return new HibernateBundle<CoreConfiguration>(User.class, Community.class, Topic.class, Comment.class, UserCommunity.class, UserTopic.class ) {

			@Override
			public DataSourceFactory getDataSourceFactory(final CoreConfiguration configuration) {
				return configuration.getDatasource();
			}
		};
	}
}
