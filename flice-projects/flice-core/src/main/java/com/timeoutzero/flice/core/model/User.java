package com.timeoutzero.flice.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User extends AbstractEntity {

	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	//Comunidades
	//Topicos
}
