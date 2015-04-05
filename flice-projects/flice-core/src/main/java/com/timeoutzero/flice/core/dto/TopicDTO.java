package com.timeoutzero.flice.core.dto;

import lombok.Getter;

import org.joda.time.LocalDateTime;

import com.timeoutzero.flice.core.model.Topic;

@Getter
public class TopicDTO {

	private String name;
	
	private Long communityId;

	private String owner;

	private LocalDateTime created;
	
	public TopicDTO(Topic topic){
		super();
		this.name = topic.getName();
		this.communityId = topic.getCommunity().getId();
		this.owner = topic.getOwner().getName();
		this.created = topic.getCreated();
	}
}
