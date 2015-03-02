package com.timeoutzero.flice.core.init;

import io.dropwizard.Configuration;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
public class CoreConfiguration extends Configuration{

	@JsonProperty
	private String application;
}
