package com.timeoutzero.flice.core.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserCommunity extends AbstractEntity{

	@ManyToOne
	private User user;
	
	@ManyToOne
	private Community community;

	private Boolean favorite;
	
}
