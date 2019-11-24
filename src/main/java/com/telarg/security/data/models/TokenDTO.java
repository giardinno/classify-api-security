package com.telarg.security.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.telarg.security.data.entities.User;

public class TokenDTO {
	
	@JsonProperty("id_token")
	private String idToken;

	private User user;

	public String getIdToken() {
		return idToken;
	}

	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}

	public User getUser() { return user; }

	public void setUser(User user) { this.user = user; }

}