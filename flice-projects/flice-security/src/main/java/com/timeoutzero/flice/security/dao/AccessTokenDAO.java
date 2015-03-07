package com.timeoutzero.flice.security.dao;

import io.dropwizard.hibernate.AbstractDAO;

import java.time.LocalDateTime;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.timeoutzero.flice.security.model.AccessToken;

public class AccessTokenDAO extends AbstractDAO<AccessToken>{
	
	@Inject
	public AccessTokenDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public AccessToken generateAccessToken(String email) {
		
		AccessToken accessToken = new AccessToken(email);
		accessToken = persist(accessToken);
		
		return accessToken;
	}
	
	public Optional<AccessToken> findByValue(String accessToken){
		
		Query query = namedQuery("AccessToken.findByValue");
		query.setParameter("value", accessToken);
		
		AccessToken result = uniqueResult(query);
		
		if(result == null){
			return Optional.absent();
		}
		
		return Optional.of(result);
	}

	public AccessToken updateLastAccessTime(Long id) {
		
		AccessToken accessToken = get(id);
		accessToken.setLastAccess(LocalDateTime.now());
		
		accessToken = persist(accessToken);
		
		return accessToken;
	}
	
	public Session getSession(){
		return super.currentSession();
	}
	
}