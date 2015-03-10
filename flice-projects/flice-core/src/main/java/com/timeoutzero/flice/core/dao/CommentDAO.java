package com.timeoutzero.flice.core.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.timeoutzero.flice.core.model.Comment;

import io.dropwizard.hibernate.AbstractDAO;

public class CommentDAO extends AbstractDAO<Comment>{

	@Inject
	public CommentDAO(SessionFactory sessionFactory){
		super(sessionFactory);
	}
	
	public Comment save(Comment comment){
		return persist(comment);
	}
	
	public Comment load(Long id){
		return get(id);
	}
	
	public Comment loadActive(Long id){
		Criteria criteria = criteria();
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("active", true));
		
		return uniqueResult(criteria);
	}
	
	public List<Comment> list(){
		Criteria criteria = criteria();
		criteria.add(Restrictions.eq("active", true));
		
		return list(criteria);
	}
	
}
