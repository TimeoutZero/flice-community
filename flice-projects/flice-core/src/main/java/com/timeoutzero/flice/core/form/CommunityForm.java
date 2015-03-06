package com.timeoutzero.flice.core.form;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.timeoutzero.flice.core.model.Community;

import lombok.Setter;

@Setter
public class CommunityForm {

	@NotBlank
	@JsonProperty
	private String name;
	
	@NotBlank
	@JsonProperty
	private String description;

	@JsonProperty
	private String image;
	
	public Community toEntity(){
		
		Community community = new Community();
		community.setName(this.name);
		community.setDescription(this.description);
		community.setImage(this.image);
		
		return community;
	}
}
