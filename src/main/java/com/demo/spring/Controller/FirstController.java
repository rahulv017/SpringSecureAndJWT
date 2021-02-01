package com.demo.spring.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.DAO.UserRepository;
import com.demo.spring.Model.ApiResponse;
import com.demo.spring.Model.Hotel;
import com.demo.spring.Model.JWTOKEN;
import com.demo.spring.Model.JwtAuthenticationResponse;
import com.demo.spring.Model.User;
import com.demo.spring.Service.HotelService;
import com.demo.spring.Service.MyUserDetails;
import com.demo.spring.Service.UserBasedService;
import com.demo.spring.Service.UserService;

@RestController
public class FirstController {
	
	@Autowired
	HotelService hotelS;
	
	@Autowired
	JWTOKEN tokenProvider;
	
	@Autowired
	UserService userS;
	@Autowired
	UserBasedService myD;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userR;
	
	@PostMapping("/auth")
	public ResponseEntity<JwtAuthenticationResponse> Autheticate(@RequestBody User user)
	
	{
		
			System.out.println(user.getPassword()+" "+ user.getUsername()+"MKBK");
	 Authentication user1=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getId(), user.getPassword())
);
		//	UserDetails user1=myD.loadUserByUsername(user.getUsername());
			
	 SecurityContextHolder.getContext().setAuthentication(user1);
			String jwt = tokenProvider.generateToken(user1);
			System.out.print("KKKKKKK");
	        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
		
	}
	@GetMapping("/hello")
	public String hello()
	{
		return "Hello!! There";
	}
	
	
	
	@GetMapping("/users")
	public List<User> getAllUsers()
	{
		return this.userR.findAll();
	}
	
	@PostMapping("/users")
	public ResponseEntity<ApiResponse> addUsers(@RequestBody User user)
	{
		this.userS.saveUser(user);
		return ResponseEntity.ok(new ApiResponse("User got saved successfully",HttpStatus.OK));
	}
	
	

}
