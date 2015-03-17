package com.timeoutzero.flice.core.dao;

import io.dropwizard.hibernate.AbstractDAO;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.timeoutzero.flice.core.model.Community;
import com.timeoutzero.flice.core.model.User;
import com.timeoutzero.flice.core.model.UserCommunity;

public class UserCommunityDAO extends AbstractDAO<UserCommunity> {

	@Inject
	public UserCommunityDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public UserCommunity save(UserCommunity userCommunity) {
		return persist(userCommunity);
	}
	
	public UserCommunity findByUserAndCommunity(User user, Community community){
		
		Criteria criteria = criteria();
		criteria.add(Restrictions.eq("user.id", user.getId()));
		criteria.add(Restrictions.eq("community.id", community.getId()));
		
		return uniqueResult(criteria);
	}

	public UserCommunity findByUser(User user){
		
		Criteria criteria = criteria();
		criteria.add(Restrictions.eq("user.id", user.getId()));
		
		return uniqueResult(criteria);
	}

	public UserCommunity findFavoriteByUser(User user){
		
		Criteria criteria = criteria();
		criteria.add(Restrictions.eq("user.id", user.getId()));
		criteria.add(Restrictions.eq("favorite", true));
		
		return uniqueResult(criteria);
	}

}
