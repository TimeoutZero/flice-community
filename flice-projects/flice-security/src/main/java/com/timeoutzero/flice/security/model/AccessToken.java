package com.timeoutzero.flice.security.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import lombok.Getter;
import lombok.Setter;

import org.joda.time.DateTime;

import com.sun.jersey.core.util.Base64;

@Entity
@Getter
@Setter
@NamedQueries({
	@NamedQuery(name = "AccessToken.findByEmail", query = "from AccessToken a where a.email = :email")
})
public class AccessToken extends AbstractEntity {

	private String email;
	private String token;
	private DateTime lastAccess;
	
	public AccessToken() {
		super();
	}
	
	public AccessToken(String email) {
		
		DateTime lastAccess = DateTime.now();
		String parameters = this.email + this.lastAccess;
		
		this.email		= email;
		this.token 		= new String(Base64.encode(parameters));
		this.lastAccess = lastAccess;
	}
}