package com.demo.spring.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.spring.DAO.UserRepository;
import com.demo.spring.Model.User;



@Service
public class UserBasedService implements UserDetailsService{

	@Autowired
	UserRepository userR;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		this.userR.findAll().forEach(user -> System.out.println(user.getUsername()));
		Optional<User> use=this.userR.findById(username);
		User uk=use.get();
		
		System.out.println("Hellooooo"+ uk.getPassword());
		return new MyUserDetails(uk);
	}

}
