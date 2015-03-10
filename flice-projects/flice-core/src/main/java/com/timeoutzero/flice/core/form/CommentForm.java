package com.timeoutzero.flice.core.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.timeoutzero.flice.core.model.Comment;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentForm {

	@NotBlank
	@JsonProperty
	private String content;
	
	@NotNull
	private Long topicId;
	
	public Comment toEntity(){
		Comment comment = new Comment();
		comment.setContent(this.content);
		
		return comment;
	}
	
}
