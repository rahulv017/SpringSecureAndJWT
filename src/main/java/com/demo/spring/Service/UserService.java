package com.demo.spring.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring.DAO.UserRepository;
import com.demo.spring.Model.User;

@Service
public class UserService {

	
	@Autowired
	UserRepository userR;
	public void saveUser(User user)
	{
		this.userR.save(user);
	}
	
	public List<User> getAllUsers()
	{
		return this.userR.findAll();
	}
 }
