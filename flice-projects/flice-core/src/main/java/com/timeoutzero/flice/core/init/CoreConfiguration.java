package com.timeoutzero.flice.core.init;

import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
public class CoreConfiguration extends Configuration{

	@Valid
	@JsonProperty
	private String application;
	
	@Valid
	@JsonProperty
	private String applicationClientId;
		
	@Valid
	@NotNull
	@JsonProperty
	private String securityApiUrl;
	
	@Valid
	@NotNull
	@JsonProperty
	private DataSourceFactory datasource;
	
	@Valid
	@NotNull
	@JsonProperty
	private FlywayFactory flyway;
	 
	@Valid
    @NotNull
    @JsonProperty
    private HttpClientConfiguration httpClient = new HttpClientConfiguration();
	
}
