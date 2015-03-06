package com.timeoutzero.flice.core.dao;

import javax.inject.Inject;

import org.hibernate.SessionFactory;

import com.timeoutzero.flice.core.model.Comment;

import io.dropwizard.hibernate.AbstractDAO;

public class CommentDAO extends AbstractDAO<Comment>{

	@Inject
	public CommentDAO(SessionFactory sessionFactory){
		super(sessionFactory);
	}
	
	public Comment create(Comment comment){
		return persist(comment);
	}
	
}
