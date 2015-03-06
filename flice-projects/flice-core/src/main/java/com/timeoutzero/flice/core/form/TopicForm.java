package com.timeoutzero.flice.core.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.timeoutzero.flice.core.model.Topic;

import lombok.Setter;

@Setter
public class TopicForm {

	@NotBlank
	@JsonProperty
	private String name;
	
	@NotNull
	@JsonProperty
	private Long communityId;
	
	public Topic toEntity(){
		Topic topic = new Topic();
		topic.setName(this.name);
		
		return topic;
	}
	
}
