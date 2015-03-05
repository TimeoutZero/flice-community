package com.timeoutzero.flice.security.dao;

import io.dropwizard.hibernate.AbstractDAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;

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
	
	public Optional<AccessToken> findByEmail(String id){
		
		Query query = namedQuery("AccessToken.findByEmail");
		AccessToken accessToken = uniqueResult(query);
		
		if(accessToken == null){
			return Optional.absent();
		}
		
		return Optional.of(accessToken);
	}

	public AccessToken updateLastAccessTime(String id) {
		
		AccessToken accessToken = get(id);
		accessToken.setLastAccess(DateTime.now());
		
		accessToken = persist(accessToken);
		
		return accessToken;
	}
	
	public Session getSession(){
		return super.currentSession();
	}
	
}