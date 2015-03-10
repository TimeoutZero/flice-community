package com.timeoutzero.flice.core.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.timeoutzero.flice.core.model.Topic;

import io.dropwizard.hibernate.AbstractDAO;

public class TopicDAO extends AbstractDAO<Topic>{

	@Inject
	public TopicDAO(SessionFactory sessionFactory){
		super(sessionFactory);
	}
	
	public Topic save(Topic topic){
		return persist(topic);
	}
	
	public Topic load(Long id){
		return get(id);
	}
	
	public Topic loadActive(Long id){
		Criteria criteria = criteria();
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("active", true));
		return uniqueResult(criteria);
	}
	
	public List<Topic> list(){
		Criteria criteria = criteria();
		criteria.add(Restrictions.eq("active", true));
		
		return list(criteria);
	}
	
}
