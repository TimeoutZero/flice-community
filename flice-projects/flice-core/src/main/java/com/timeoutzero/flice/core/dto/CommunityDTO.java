package com.timeoutzero.flice.core.dto;

import lombok.Getter;

import org.joda.time.LocalDateTime;

import com.timeoutzero.flice.core.model.Community;

@Getter
public class CommunityDTO {
	
	private Long id;

	private String name;
	
	private String description;
	
	private String owner;
	
	private LocalDateTime created;

	private String image;
	
	public CommunityDTO(Community community){
		super();
		
		this.id	 		 = community.getId();
		this.name 		 = community.getName();
		this.description = community.getDescription();
		this.owner  	 = community.getOwner().getName();
		this.created 	 = community.getCreated();
		this.image 		 = community.getImage();
	}
	
}
