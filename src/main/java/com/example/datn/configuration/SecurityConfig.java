package com.example.datn.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.example.datn.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  
	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	private final LogoutHandler logoutHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	  http
	      .csrf()
	      .disable()
	      .authorizeHttpRequests()
	      .requestMatchers("/api/v1/auth/**")
	        .permitAll()
	      .requestMatchers("/api/v1/account/**")
	      	.permitAll()
	      .requestMatchers("/api/v1/restaurant/**")
	      	.permitAll()
		  .requestMatchers("/api/v1/insert-data/**")
		    .permitAll()
	      .anyRequest()
	        .authenticated()
	      .and()
	        .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	      .and()
	      .authenticationProvider(authenticationProvider)
	      .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
	      .logout()
	      .logoutUrl("/api/v1/auth/logout")
	      .addLogoutHandler(logoutHandler)
	      .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
	  return http.build();
	}

}

