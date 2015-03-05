package com.timeoutzero.flice.core.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Topic extends AbstractEntity{

	@Column(name="name")
	private String name;
	
	@ManyToOne
	private Community community;
	
	@ManyToOne
	private User owner;
	
	@Column(name="created")
	private LocalDateTime created;
	
	@Column(name="active")
	private Boolean active;
}
