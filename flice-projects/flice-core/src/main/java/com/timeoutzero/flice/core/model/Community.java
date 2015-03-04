package com.timeoutzero.flice.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Community extends AbstractEntity{

	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
}
