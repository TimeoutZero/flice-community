package com.timeoutzero.flice.security.model;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
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
		
		SecureRandom random = new SecureRandom();
		byte[] key = new byte[64];
		random.nextBytes(key);
		
		LocalDateTime expiration = LocalDateTime.now().plusDays(1);
		Date convertedExpiration = Date.from(expiration.atZone(ZoneId.systemDefault()).toInstant());
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("admin", false);
		
		String token = Jwts.builder()
				.setSubject(email)
				.setExpiration(convertedExpiration)
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS256, key)
				.compact();
		
		this.email		= email;
		this.token 		= token;
		this.lastAccess = LocalDateTime.now();
	}
}