package com.telarg.security.data.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;

	@NotEmpty
	private String name;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return name;
	}

	public User getUser() { return user; }

	public void setUser(User user) { this.user = user; }
}