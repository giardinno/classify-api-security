package com.telarg.security;

import com.telarg.security.utils.InitBD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;

import com.telarg.security.configurations.CustomUserDetailService;
import com.telarg.security.security.CustomEntryPoint;
import com.telarg.security.security.CustomSuccessHandler;
import com.telarg.security.security.CustomcustomFailureHandler;
import com.telarg.security.security.JsonAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@EnableFeignClients
@SpringBootApplication
@EnableConfigurationProperties
public class ServicioApplication extends WebSecurityConfigurerAdapter implements CommandLineRunner {
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private CustomEntryPoint customEntryPoint;
	
	@Autowired
	private CustomSuccessHandler customSuccessHandler;
	
	@Autowired
	private CustomcustomFailureHandler customFailureHandler;

	@Autowired
	private InitBD initBD;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService);
	}

	@Bean
	protected CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {


		http
			.exceptionHandling()
			.authenticationEntryPoint(customEntryPoint);

		http.cors()
			.and().authorizeRequests().anyRequest().permitAll()
				.and().csrf().disable();

		http.formLogin().permitAll();

		http.addFilterAt( customAuthenticationFilter() , UsernamePasswordAuthenticationFilter.class );

		/*
		http
				.exceptionHandling()
				.authenticationEntryPoint(customEntryPoint);

		http.cors()
				.and()
				.csrf().disable()
				.authorizeRequests().antMatchers("/**").authenticated();

		http.formLogin().permitAll();

		http.addFilterAt( customAuthenticationFilter() , UsernamePasswordAuthenticationFilter.class );
		*/


	}
	
	private JsonAuthenticationFilter customAuthenticationFilter() throws Exception {
		JsonAuthenticationFilter filter = new JsonAuthenticationFilter();
		filter.setAuthenticationFailureHandler(customFailureHandler);
		filter.setAuthenticationSuccessHandler(customSuccessHandler);
        filter.setAuthenticationManager(authenticationManager());
        filter.setFilterProcessesUrl("/authenticate");
        return filter;
    }
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	HeaderHttpSessionIdResolver sessionStrategy() {
		return new HeaderHttpSessionIdResolver("Authorization");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ServicioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initBD.init();
	}
	
}