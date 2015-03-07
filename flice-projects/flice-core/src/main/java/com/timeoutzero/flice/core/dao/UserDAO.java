package com.timeoutzero.flice.core.dao;

import io.dropwizard.hibernate.AbstractDAO;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.timeoutzero.flice.core.model.User;

public class UserDAO extends AbstractDAO<User> {

	@Inject
	public UserDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public User create(User user) {
		return persist(user);
	}
	
	public User findByEmail(String email){
		
		Query namedQuery = namedQuery("User.findByEmail");
		namedQuery.setParameter("email", email);
		
		return uniqueResult(namedQuery);
	}
}
