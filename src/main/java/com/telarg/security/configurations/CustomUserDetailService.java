package com.telarg.security.configurations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.telarg.security.data.entities.User;
import com.telarg.security.repositories.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> posibleUser = userRepository.findByName(username);
		if( posibleUser.isPresent() ) {
			User user = posibleUser.get();
			return new org.springframework.security.core.userdetails.User( user.getName(), "{noop}" + user.getPassword(), user.getRoles() );
		}
		throw new UsernameNotFoundException(username);
	}

}
