package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final String USER = "user";
	private final String ADMIN = "admin";
	
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers(
				"/v2/api-docs", 
                "/configuration/ui", 
                "/swagger-resources/**",
                "/configuration/security", 
                "/swagger-ui.html", 
                "/webjars/**"
				);
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		String passwordUser = passwordEncoder().encode("abc123");
		String passwordRoot = passwordEncoder().encode("def456");
		
		auth.inMemoryAuthentication()
			.passwordEncoder(passwordEncoder())
			.withUser("user")
			.password(passwordUser)
			.roles(USER)
		 .and()
		    .passwordEncoder(passwordEncoder())
			.withUser("admin")
			.password(passwordRoot)
			.roles(USER,ADMIN);
	}
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
