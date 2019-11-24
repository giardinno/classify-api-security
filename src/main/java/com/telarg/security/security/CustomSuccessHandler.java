package com.telarg.security.security;

import java.io.IOException;;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.telarg.security.data.models.TokenDTO;
import com.telarg.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
		String token = RequestContextHolder.currentRequestAttributes().getSessionId();
		TokenDTO tokenDTO = new TokenDTO();
		tokenDTO.setIdToken(token);
		try {
			tokenDTO.setUser( userRepository.findByName( SecurityContextHolder.getContext().getAuthentication().getName() ).get() );
			tokenDTO.getUser().setPassword(null);
		}catch (Exception e){
			System.out.println(e);
		}
		response.addHeader("Content-Type", "application/json");
		response.getWriter().write( new ObjectMapper().writeValueAsString( tokenDTO ) );
		response.setStatus( HttpServletResponse.SC_OK );
	}
	
}