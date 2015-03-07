package com.timeoutzero.flice.security.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import lombok.Getter;
import lombok.Setter;

import com.sun.jersey.core.util.Base64;

@Entity
@Getter
@Setter
@NamedQueries({
	@NamedQuery(name = "AccessToken.findByValue", query = "from AccessToken a where a.token = :value")
})
public class AccessToken extends AbstractEntity {

	private String email;
	private String token;
	private LocalDateTime lastAccess;
	
	public AccessToken() {
		super();
	}
	
	public AccessToken(String email) {
		
		LocalDateTime lastAccess = LocalDateTime.now();
		String parameters = this.email + this.lastAccess;
		
		this.email		= email;
		this.token 		= new String(Base64.encode(parameters));
		this.lastAccess = lastAccess;
	}
}