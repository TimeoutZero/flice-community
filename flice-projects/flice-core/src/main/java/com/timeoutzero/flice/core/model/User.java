package com.timeoutzero.flice.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@NamedQueries({
	@NamedQuery(name ="User.findByEmail", query = "from User u where u.email = :email")
})
public class User extends AbstractEntity {

	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
}
