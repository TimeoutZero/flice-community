package com.timeoutzero.flice.core.dao;

import io.dropwizard.hibernate.AbstractDAO;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.timeoutzero.flice.core.model.Topic;
import com.timeoutzero.flice.core.model.User;
import com.timeoutzero.flice.core.model.UserTopic;

public class UserTopicDAO extends AbstractDAO<UserTopic> {

	@Inject
	public UserTopicDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public UserTopic save(UserTopic userTopic) {
		return persist(userTopic);
	}
	
	public UserTopic findByUserAndTopic(User user, Topic topic){
		
		Criteria criteria = criteria();
		criteria.add(Restrictions.eq("user.id", user.getId()));
		criteria.add(Restrictions.eq("topic.id", topic.getId()));
		
		return uniqueResult(criteria);
	}

	public UserTopic findByUser(User user){
		
		Criteria criteria = criteria();
		criteria.add(Restrictions.eq("user.id", user.getId()));
		
		return uniqueResult(criteria);
	}

	public UserTopic findFavoriteByUser(User user){
		
		Criteria criteria = criteria();
		criteria.add(Restrictions.eq("user.id", user.getId()));
		criteria.add(Restrictions.eq("favorite", true));
		
		return uniqueResult(criteria);
	}

}
