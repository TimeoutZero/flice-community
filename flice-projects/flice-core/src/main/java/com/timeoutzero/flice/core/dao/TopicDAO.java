package com.timeoutzero.flice.core.dao;

import javax.inject.Inject;

import org.hibernate.SessionFactory;

import com.timeoutzero.flice.core.model.Topic;

import io.dropwizard.hibernate.AbstractDAO;

public class TopicDAO extends AbstractDAO<Topic>{

	@Inject
	public TopicDAO(SessionFactory sessionFactory){
		super(sessionFactory);
	}
	
	public Topic create(Topic topic){
		return persist(topic);
	}
	
}
