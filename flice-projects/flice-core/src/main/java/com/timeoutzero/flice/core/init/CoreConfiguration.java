package com.timeoutzero.flice.core.init;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
public class CoreConfiguration extends Configuration{

	@JsonProperty
	private String application;
	
	@JsonProperty("application-client-id")
	private String applicationClientId;
	
	@Valid
	@NotNull
	@JsonProperty
	private DataSourceFactory datasource;
	
}
