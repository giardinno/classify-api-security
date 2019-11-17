package com.telarg.security.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenDTO {
	
	@JsonProperty("id_token")
	private String idToken;

	public String getIdToken() {
		return idToken;
	}

	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}
	
}
