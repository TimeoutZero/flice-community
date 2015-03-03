package com.timeoutzero.flice.core.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;

@Getter
@MappedSuperclass
public class AbstractEntity {

	@Id
	@GeneratedValue
	private Long id;
}
