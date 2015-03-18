package com.timeoutzero.flice.core.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserTopic extends AbstractEntity {

	@ManyToOne
	private User user;
	
	@ManyToOne
	private Topic topic;
	
	private Boolean favorite;
	
}
