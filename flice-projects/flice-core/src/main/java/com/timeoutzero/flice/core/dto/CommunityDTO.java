package com.timeoutzero.flice.core.dto;

import java.time.LocalDateTime;

import com.timeoutzero.flice.core.model.Community;

import lombok.Getter;

@Getter
public class CommunityDTO {

	private String name;
	
	private String description;
	
	private String owner;
	
	private LocalDateTime created;

	private String image;
	
	public CommunityDTO(Community community){
		super();
		this.name = community.getName();
		this.description = community.getDescription();
		this.owner = community.getOwner().getName();
		this.created = community.getCreated();
		this.image = community.getImage();
	}
	
}
