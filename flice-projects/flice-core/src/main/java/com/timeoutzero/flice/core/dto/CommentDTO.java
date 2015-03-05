package com.timeoutzero.flice.core.dto;

import java.time.LocalDateTime;

import com.timeoutzero.flice.core.model.Comment;

import lombok.Getter;

@Getter
public class CommentDTO {

	private String content;
	
	private Long topicId;
	
	private String owner;
	
	private LocalDateTime created;
	
	public CommentDTO(Comment comment){
		super();
		this.content = comment.getContent();
		this.topicId = comment.getTopic().getId();
		this.owner = comment.getOwner().getName();
		this.created = comment.getCreated();
	}
}
