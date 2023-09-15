package com.example.demo.oneStopShop.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class AppConfig {
	
	@Bean
	public SecurityFilterChain securityConfig(HttpSecurity http) throws Exception {
		
		http.csrf(csrf->csrf.disable());
		
		http.cors(Customizer.withDefaults());
		
		http.authorizeHttpRequests(authorize->authorize
				.requestMatchers(HttpMethod.GET,"/admin/**")
				.permitAll()
				.requestMatchers(HttpMethod.POST,"/admin/**")
				.permitAll()
				.requestMatchers(HttpMethod.POST,"/signUp")
				.permitAll()
				.anyRequest()
				.authenticated());
		
		http.sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.addFilterBefore(new JwtTokenGenerator(), BasicAuthenticationFilter.class);
		
		http.addFilterAfter(new JwtTokenValidator(), BasicAuthenticationFilter.class);
		
		http.httpBasic(Customizer.withDefaults());
		
		http.formLogin(Customizer.withDefaults());
		
		return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
