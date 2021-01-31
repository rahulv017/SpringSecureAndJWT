package com.demo.spring.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.demo.spring.Filters.JwtFilter;
import com.demo.spring.Model.JWTOKEN;


@Configuration
public class GlobalConfig {

	
	@Bean
	public PasswordEncoder getPassword()
	{
		return  NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public JwtFilter jwtkon()
	{
		return new JwtFilter();
	}
}
