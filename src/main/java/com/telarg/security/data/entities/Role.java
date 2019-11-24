package com.telarg.security.data.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
public class Role implements GrantedAuthority {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;

	@NotEmpty
	private String name;

	@ManyToMany
	private Set<User> user;

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

	public Set<User> getUser() { return user; }

	public void setUser(Set<User> user) { this.user = user; }
}