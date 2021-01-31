package com.demo.spring.Service;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.demo.spring.DAO.UserRepository;
import com.demo.spring.Model.User;

public class MyUserDetails implements UserDetails{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	User userR;
	private String user;
	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	
	MyUserDetails(String username)
	{
		this.user=username;
	}
	
	MyUserDetails(User us)
	{
		this.userR=us;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		System.out.print("hello"+ this.userR.getPassword());
		//this.userR.findAll().forEach(user -> System.out.println(user.getUsername()));
		return this.userR.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
