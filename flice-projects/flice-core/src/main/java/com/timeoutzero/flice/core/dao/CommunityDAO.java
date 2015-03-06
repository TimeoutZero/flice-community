package com.timeoutzero.flice.core.dao;

import javax.inject.Inject;

import org.hibernate.SessionFactory;

import com.timeoutzero.flice.core.model.Community;

import io.dropwizard.hibernate.AbstractDAO;

public class CommunityDAO extends AbstractDAO<Community>{

	@Inject
	public CommunityDAO(SessionFactory sessionFactory){
		super(sessionFactory);
	}
	
	public Community create(Community community){
		return persist(community);
	}

}
