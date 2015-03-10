package com.timeoutzero.flice.core.dao;

import java.util.List;

import io.dropwizard.hibernate.AbstractDAO;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.timeoutzero.flice.core.model.Community;

public class CommunityDAO extends AbstractDAO<Community>{

	@Inject
	public CommunityDAO(SessionFactory sessionFactory){
		super(sessionFactory);
	}
	
	public Community save(Community community){
		return persist(community);
	}
	
	public Community load(Long id){
		return get(id);
	}
	
	public Community loadActive(Long id){
		Criteria criteria = criteria();
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("active", true));
		
		return uniqueResult(criteria);
	}
	
	public List<Community> list(){
		Criteria criteria = criteria();
		criteria.add(Restrictions.eq("active", true));
		
		return list(criteria);
	}

}
