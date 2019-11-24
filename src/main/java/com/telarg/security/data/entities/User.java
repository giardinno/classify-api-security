package com.telarg.security.data.entities;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@NotEmpty
	private String name;
	
	@NotEmpty
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;

	public User() { }
	
	public User(String name, @NotEmpty String password, Set<Role> roles) {
		super();
		this.name = name;
		this.password = password;
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}