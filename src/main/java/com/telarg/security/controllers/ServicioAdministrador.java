package com.telarg.security.controllers;

import java.util.List;
import java.util.Optional;

import com.telarg.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telarg.security.data.entities.User;

@RestController
@RequestMapping("/administrador")
public class ServicioAdministrador {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users")
	public Iterable<User> createUser( ) {
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{user}")
	public User createUser( @PathVariable(name = "user") String userName ) {
		Optional<User> posibleUser = userRepository.findByName(userName);
		if( posibleUser.isPresent() ) {
			return posibleUser.get();
		}
		return null;
	}
	
	@PostMapping("/users")
	public User createUser( @RequestBody() User user ) {
		userRepository.save(user);
		return user;
	}
	
}
