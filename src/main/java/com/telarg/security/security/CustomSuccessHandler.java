package com.telarg.security.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.telarg.security.data.entities.User;
import com.telarg.security.data.models.TokenDTO;
import com.telarg.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.util.ContentCachingRequestWrapper;

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
			ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
			try ( InputStream is = requestWrapper.getInputStream() ) {
				DocumentContext context = JsonPath.parse(is);
				String username = context.read("$.username", String.class);
				String password = context.read("$.password", String.class);
				tokenDTO.setUser(userRepository.findByName(username).get());
				tokenDTO.getUser().setPassword(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}catch (Exception e){
			System.out.println(e);
		}

		response.addHeader("Content-Type", "application/json");
		response.getWriter().write( new ObjectMapper().writeValueAsString( tokenDTO ) );
		response.setStatus( HttpServletResponse.SC_OK );
	}
	
}