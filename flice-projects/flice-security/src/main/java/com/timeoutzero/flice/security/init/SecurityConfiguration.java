package com.timeoutzero.flice.security.init;

import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;

@Getter
public class SecurityConfiguration extends Configuration {

	@Valid
	@NotNull
	@JsonProperty
	private String application;
	
	@Valid
	@NotNull
	@JsonProperty("application-client-id")
	private String applicationClientId;

	@Valid
	@NotNull
	private String coreApiUrl;
	
	@Valid
	@NotNull
	@JsonProperty
	private DataSourceFactory datasource = new DataSourceFactory();
	
	@Valid
	@JsonProperty
	private ImmutableList<String> allowedGrantTypes;
	
	@Valid
    @NotNull
    @JsonProperty
    private HttpClientConfiguration httpClient = new HttpClientConfiguration();


}
