package com.timeoutzero.flice.security.api;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccessTokenDTO {

	@NotNull
	private String accessToken;
}
