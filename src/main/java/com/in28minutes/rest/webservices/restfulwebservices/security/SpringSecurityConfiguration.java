package com.in28minutes.rest.webservices.restfulwebservices.security;

//import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;




//@Configuration
public class SpringSecurityConfiguration {

//	InMemoryUserDetailsManager
//	InMemoryUserDetailsManager(UserDetails... users)
	

	//all url are protected
	//login form is shown for unauthorized request
	//CSRF disable
	//by default, h2 database uses frames in html but 
	//spring doesn't allow it show we need to deal with the frames also 
	
	
//	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return 
				http
				    .authorizeHttpRequests(
				         auth -> 
				             auth
//				             .requestMatchers(mvcMatcherBuilder.pattern(API_URL_PATTERN)).permitAll()   HttpMethod.OPTIONS,
//				             .requestMatchers( "/**").permitAll()
				            .anyRequest().authenticated()
				)
		        .httpBasic(Customizer.withDefaults())
	             .sessionManagement(
				         session -> session.sessionCreationPolicy
			          	(SessionCreationPolicy.STATELESS))
		
		
//		http.csrf().disable();  -----> deprected
////		http.headers().frameOptions().disable();   -----> deprected
				
		        .csrf(csrf -> csrf.disable())
	            .build();
	} 
	
	
}
